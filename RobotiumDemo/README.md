# 自动化测试框架 Rebotium的简单使用

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
1. 下载jar包,放到AS工程模块 `app/libs/` 目录下,之后右键 `Add as Library...`即可,可以发现在 `app/build.gradle` 中多了一项:
```gradle
compile files('libs/robotium-solo-5.6.0.jar')
``` 

2. 直接使用gradle导入线上版本:
```gradle
# app/build.gradle
dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    testCompile 'junit:junit:4.12'
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
5. 重写 `setUp()` 方法,初始化Solo对象
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
待添加...