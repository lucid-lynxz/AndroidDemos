1. 在 `app/build.gradle` 中添加依赖:
```gradle
android {
    ......
    testOptions {
        unitTests.returnDefaultValues = true
    }
}
dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    testCompile 'junit:junit:4.12'
    testCompile "org.robolectric:robolectric:3.0"
}
```

2. 同步后,会自动生成配置文件 `BuildConfig.java` :
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
public class MainActivityTest {// 这里如果有默认的extends TestCase的话,删掉

    private ActivityController<MainActivity> mController;
    private MainActivity mMainActivity;

    //在@Before注解的方法中进行参数预设置或者初始化,便于后面测试用例(@Test标注的方法)使用
    @Before
    public void setUp() {
        mController = Robolectric.buildActivity(MainActivity.class).create();
        Assert.assertNotNull(mController);
        // 通过controller对页面生命周期进行触发调用
        mController.resume().destroy(); 

        // 获取页面实例
        mMainActivity = mController.get();
    }

    @Test
    public void testView() throws Exception {
        // 对页面控件的操作跟正常编写程序一致
        TextView tvMain = mMainActivity.findView(R.id.tv_main);
        tvMain.setText("hello");
        Assert.assertEquals("onResume", tvMain.getText().toString().trim());

        // 要触发按钮事件,调用view.performClick()即可
        Button btnStartActivity = mMainActivity.findView(R.id.btn_main);
        btnStartActivity.performClick();

        // 页面跳转判断
        Intent expectedIntent = new Intent(mMainActivity, SecondActivity.class);
        Intent actualIntent = ShadowApplication.getInstance().getNextStartedActivity();
        Assert.assertEquals(expectedIntent, actualIntent);

        // dialog 测试
        mMainActivity.showCustomDialog(); // 调用页面方法显示dialog,这是提高sonar覆盖率的有效方法
        AlertDialog latestAlertDialog = ShadowAlertDialog.getLatestAlertDialog();// 实际显示的dialog
        Assert.assertNotNull(latestAlertDialog);

        // toast 测试        
        mMainActivity.showToast("roboletric");
        String textOfLatestToast = ShadowToast.getTextOfLatestToast();//获取实际toast内容
        Assert.assertEquals("roboletric", textOfLatestToast);
    }

    /**
     * 对retrofit api请求进行测试
     * 走真实网络请求,并返回
     * 执行时,通过toBlocking() 方法来阻塞直接显示结果
     *
     * @throws Exception
     */
    @Test
    public void testRestApi() throws Exception {
        GithubApiService apiService = new GithubRetrofit().getApiService();
        Observable<List<Follower>> followers = apiService.getFollowers("lynxz");
        followers.toBlocking().forEach(followers1 -> System.out.println(followers1));
    }

    @Test
    public void testGetCallback() throws Exception {
        GithubApiService apiService = new GithubRetrofit().getApiService();
        Call<List<Follower>> followers = apiService.getFollowersCall("lynxz");
        // 立即执行,若写成 followers.enqueue(...) 则添加到队列后无法对结果进行处理
        Response<List<Follower>> execute = followers.execute();
        System.out.println(" callback " + execute.body());
    }
}
```



        
