# 自动化测试框架 Rebotium的简单使用
[官网 step-by-step tutorials](https://github.com/RobotiumTech/robotium/wiki/Robotium-Tutorials)

1. 待测试项目在 RobotiumDemo/ 中,AS向导中的LoginActivity,两个输入框加一个登录按钮而已;
2. 修改 `Build` -- `Select Build Variant`:
    1. 我这里要在真机上运行,看到效果,选择 `Test Artifact` 为 `Android Instrumenttation Tests`;
    2. 设定模块app的 `Build Variant` 为 `debug`;
3. `run` -- `Edit configurations`,在打开的 `Run/Debug Configuration` 对话框中删除 `Junit` ,添加 `Android Tests`
不然报错 [NoClassDefFoundError: junit/textui/ResultPrinter]( # http://stackoverflow.com/questions/19516289/exception-in-thread-main-java-lang-noclassdeffounderror-junit-textui-resultpr
    ):
```
java.lang.NoClassDefFoundError: junit/textui/ResultPrinter
```

## 导入
1. [下载](https://github.com/RobotiumTech/robotium/wiki/Downloads) jar包,放到AS工程模块 `app/libs/` 目录下,之后右键 `Add as Library...` 即可,可以发现在 `app/build.gradle` 中多了一项:
```gradle
compile files('libs/robotium-solo-5.6.0.jar')
``` 

2. 直接使用gradle导入线上版本:
```gradle
# app/build.gradle
dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    // testCompile 'junit:junit:4.12'
    ...
    testCompile 'com.jayway.android.robotium:robotium-solo:5.6.0'
}
```

## 白盒测试
1. 有源码时,可以直接创建测试用例,便可直接访问Activity,资源等;
2. 在要测试的页面右键, `Go to` -- `Test(ctrl+shit+t)` -- `Create new test...` 即可创建;
3. 手动继承 `ActivityInstrumentationTestCase2`;
4. 创建无参构造函数,否则会报错:
```shell
    junit.framework.AssertionFailedError: Class org.lynxz.robotiumdemoapp.LoginActivityTest has no public constructor TestCase(String name) or TestCase()
```
5. 重写 `setUp()` 方法,初始化Solo对象,各种操作基本都是通过Solo来完成
```java
    Solo solo = new Solo(getInstrumentation(), getActivity());
```
6. 创建测试方法:
```java
  // 名称任意
    public void func_name() throws Exception {
      
    }
```
7. 右键直接运行测试类即可;
8. 常用方法:
```java
    // 查找指定id的控件
    EditText edtMail = (EditText) mSolo.getView(R.id.email);

    // 输入文本
    mSolo.enterText(edtMail, "lucid_lynxz@163.com");

    // 清除文本
    mSolo.clearEditText(edtMail);

    // 点击按钮(clickOnButton不太好用...)
    mSolo.clickOnView(btn);

    // 休眠一段时间,毫秒
    mSolo.sleep(1000);

    // 截屏,储存到 /sdcard/Robotium-Screenshots/,可以带参数,指定名字
    mSolo.takeScreenshot();
```


## 黑盒测试
这里有两篇资源:
1. [Rototium框架的使用、黑盒与白盒测试](http://www.paincker.com/android-test-5)
2. [在Android Studio中进行黑盒测试（可使用Robotium）](http://www.paincker.com/android-test-7)

步骤:
1. 下载apk,重新签名为debug签名, [官网](http://robotium.googlecode.com/files/TestAndroidCalculator-WhiteBoxTesting-V2_0.pdf) 给出的小工具地址是: http://www.troido.de/re-sign.jar ,下不来就到 [网盘](http://pan.baidu.com/s/1jIg1qsQ) 下;
P.S.  
  *  使用方法也简单: 运行 `re-sign.jar` 后,将下载下来的apk拖动进去即可;
若是提示找不到找不到 `zipalign CreateProcess error=2` , 就从 `androidSDK/build-tools/` 目录下复制一个 `zipalign` 到提示目录里即可;
  * 有些apk会对签名文件进行检查或者使用其对资源文件进行加密,导致重新签名后无法运行,不过我是黑盒测试自己的应用(因为代码要使用公司的平台进行打包,会有写不同,所以黑盒测试),不考虑这个问题;
2. 安装重新签名的apk到手机上(有源码可以直接运行源码,就会安装debug版本了);
3. 新建一个工程,修改 `app/build.gradle` ,是工程包名与待测apk包名一致:
```gradle
android {

    defaultConfig {
        applicationId "org.lynxz.robotiumdemoapp" # 改成待测apk包名
    }
}
```
4. 按照白盒测试那样,新建测试类(在 `androidTest/` 目录下, `Build Variants` 配置同白盒一样),不过由于没有源码,因此对应的Activity需要通过反射来获取:
```java
    public class LoginActivityTest extends ActivityInstrumentationTestCase2 {

        public static final String PKG_NAME = "org.lynxz.robotiumdemoapp";
        public static final String ACTIVITY_NAME = "org.lynxz.robotiumdemoapp.LoginActivity";

        public LoginActivityTest() throws ClassNotFoundException {
            super(Class.forName(ACTIVITY_NAME));
        }
    }
```

5. 测试用例中,查找控件就不能使用常规的 `R.id.***` 了,可以通过text或者 `包名:id/***` 的形式来指定:
```java
	// 实际的测试内容
	public void testAutoLogin() throws Exception {
        // 查找控件,以下两种方式都可以,这里不能直接使用 'R.id.**' 来查找
        final EditText edtMail = (EditText) mSolo.getView("email");
        final EditText edtPwd = (EditText) mSolo.getView(PKG_NAME + ":id/password");
        final Button btn = (Button) mSolo.getView(PKG_NAME + ":id/email_sign_in_button");
        //对控件的操作(如输入文本,点击等)跟白盒测试一致
    }
```
6. 运行测试工程(重点)
这里就不能

## monkey
还没单独写monkey的,直接补充在这里了,记录下碰到的问题
1.  `E/hierarchyviewer: Unable to get view server version from device a5544648`
在添加 `EasyMonkeyDevice` 的时候,真机会报上述错误,代码如下:
```python
    from com.android.monkeyrunner import MonkeyRunner,MonkeyDevice
    from com.android.monkeyrunner import MonkeyImage
    from com.android.monkeyrunner.easy import EasyMonkeyDevice
    from com.android.monkeyrunner.easy import By
    
    device = MonkeyRunner.waitForConnection()
    easy_device = EasyMonkeyDevice(device)
    device.startActivity(component = 'com.nd.sdp.component.biz/com.nd.smartcan.appfactory.demo.SplashActivity')
    easy_device.touch(By.id('id/btn_login'),MonkeyDevice.DOWN_AND_UP)
```
找半天,在 [这里](http://www.51testing.com/html/34/361634-3707866.html) 找到了解释,原来需要真机中开启一个View Server的客户端与其进行socket通信,而在商业手机上,是无法开启view server的.
可以通过以下命令来查看/开关View server(我在自己未root的小米note上测试开启不成功):
```shell
    adb shell service call window 1 i32 4939 # 开启命令，用此命令开启

    adb shell service call window 2 i32 4939 # 关闭命令

    adb shell service call window 3 # 查看状态
    # 若返回值是：Result: Parcel(00000000 00000001 '........') 说明View Server处于开启状态
    # 若返回值是：Result: Parcel(00000000 00000000 '........') 说明View Server处于关闭状态
```
又发现篇开启 `View server`的 [文章](https://www.dup2.org/node/1538) ,不过我没去折腾了;
P.S. 使用 `androidSDK/tools/monkeyrunner.bat your_python_script.py` 来运行脚本;