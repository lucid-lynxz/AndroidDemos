1. 在 `app/build.gradle` 中添加依赖:
```gradle
android {
    compileSdkVersion 23
    buildToolsVersion "24.0.0"
    ......
    testOptions {
        unitTests.returnDefaultValues = true
    }
}

dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    testCompile 'junit:junit:4.12'
    testCompile "org.robolectric:robolectric:3.1"
}
```

2. 同步后,自动生成配置文件 `ConfigConfig.class` :
```java
// Automatically generated file. DO NOT MODIFY
package org.lynxz.robolectricdemo;
public final class BuildConfig {
  public static final boolean DEBUG = Boolean.parseBoolean("true");
  public static final String APPLICATION_ID = "org.lynxz.robolectricdemo";
  public static final String BUILD_TYPE = "debug";
  public static final String FLAVOR = "";
  public static final int VERSION_CODE = 1;
  public static final String VERSION_NAME = "1.0";
}
```

3. 配置 build 参数:
菜单 `Build -- Select Build Variant` : `Test Aritfact ==> Unit Tests`


4. 创建测试类:
假设存在 `MainActivity` 页面,在AS中右键 `go to  -- test(ctrol+shit+t)` 会在 `test/` 目录下创建测试类,对其进行设置即可
```java
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

// 这里得配置默认的runner,不然运行不起来,同时指定sdk版本(21及以下)
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class MainActivityTest {

    private ActivityController<MainActivity> mController;

    @Before
    public void setUp() {
        mController = Robolectric.buildActivity(MainActivity.class).create();
        Assert.assertNotNull(mController);
    }

    @Test
    public void testClick() throws Exception {
        MainActivity mainActivity = mController.get();
        mController.resume();
    }
}
```



        
