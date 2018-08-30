# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# 注意: cmake 指令的大小写无关, 但参数大小写敏感
# Sets the minimum version of CMake required to build the native library.
cmake_minimum_required(VERSION 3.4.1)
#SET(CMAKE_BUILE_TYPE RELEASE)

# 指令: PROJECT(projectname [CXX] [C] [Java]) 来设置工程名称以及支持的语言
# 设置后, cmake 就创建了两个变量: <projectname>_BINARY_DIR 以及 <projectname>_SOURCE_DIR
# 另外 cmake 系统预定义了 PROJECT_BINARY_DIR 和 PROJECT_SOURCE_DIR 变量,
# 他们的值分别跟 <projectname>_BINARY_DIR 以及 <projectname>_SOURCE_DIR 一致
# 建议直接使用 PROJECT_BINARY_DIR 和 PROJECT_SOURCE_DIR 避免后续修改项目名后也要同时修改变量名的问题
# PROJECT(HelloJni CXX)


# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.

add_library( # Sets the name of the library.
             native-lib

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
             src/main/cpp/native-lib.cpp )

# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library( # Sets the name of the path variable.
              log-lib

              # Specifies the name of the NDK library that
              # you want CMake to locate.
              log )

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.

target_link_libraries( # Specifies the target library.
                       native-lib

                       # Links the target library to the log library
                       # included in the NDK.
                       ${log-lib}
)


##### 配置开关选项 #####
# 默认为 OFF, 注意: 每次改变 ON/OFF 值,请务必清理 CMakeCache.txt 和 CMakeFile
# 简单粗暴的做法就是删除 app/build 目录 或者执行 as菜单: build-refresh linked c++ projects
# option 定义的变量可结合 `configure_file{}` 使用 `#cmakedefine use_my_math_lib` 来确定是ON或者OFF
option (use_my_math_lib "Use mySqrtLib" on)

##### 添加一个自定义库 #####
# 1. 创建一个目录用于方式独立的库,如: app/src/MathFunctions
# 2. 在创建的目录中添加 CMakeLists.txt 并按需增加c++源代码
# 3. 配置编译任务:
# 添加头文件搜索目录
if(use_my_math_lib)
    set(my_math_lib_path "${CMAKE_SOURCE_DIR}/src/main/MyMath")
    include_directories (${my_math_lib_path})
    # 将指定的文件夹加到build任务列表
    add_subdirectory (./src/main/MyMath)
    # set (ENABLE_MY_MATH_LIB 1)
    target_link_libraries( # Specifies the target library.
                           native-lib

                           # MathFunc 是自定义的 MyMath/CMakeLists.txt 中指定生成的库
                           MathFunc
    )
else(use_my_math_lib)
    # set (ENABLE_MY_MATH_LIB 0)
endif(use_my_math_lib)

# 通过message来打印信息
# 指令语法: MESSAGE([SEND_ERROR | STATUS | FATAL_ERROR] "message to display"...)
#       SEND_ERROR: 产生错误，生成过程被跳过
#       SATUS: 输出前缀为—的信息
#       FATAL_ERROR: 立即终止所有cmake过程
# 以下指令,可以把变量写在引号内也可以写在引号外
message("use_my_math_lib status =  ${use_my_math_lib}")
message(STATUS "the project_binary_dir is : " ${PROJECT_BINARY_DIR})
message(STATUS "the project_source_dir is : " ${PROJECT_SOURCE_DIR})

##### 添加版本号信息到源代码中 #####
# 1. 设置版本号值
set (VERSION_MAJOR 3)
set (VERSION_MINOR 8)

# configure a header file to pass some of the CMake settings to the source code
# 2. 设置 `app/Config.h.in` 文件(自行创建)编译后生成 `Config.h`
# 在 `app/Config.h.in` 中定义相关变量
configure_file (
  "${PROJECT_SOURCE_DIR}/Config.h.in"
  "${PROJECT_BINARY_DIR}/Config.h"
  )

# 3. 将 `Config.h` 所在目录设定为头文件目录
include_directories("${PROJECT_BINARY_DIR}")