package org.lynxz.robolectricdemo;

import android.app.AlertDialog;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lynxz.robolectricdemo.model.Follower;
import org.lynxz.robolectricdemo.network.GithubApiService;
import org.lynxz.robolectricdemo.network.GithubRetrofit;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowAlertDialog;
import org.robolectric.shadows.ShadowApplication;
import org.robolectric.shadows.ShadowToast;
import org.robolectric.util.ActivityController;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import rx.Observable;

/**
 * Created by Lynxz on 2016/7/11.
 */
//@RunWith(CustomRobolectricTestRunner.class)
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class MainActivityTest {

    private ActivityController<MainActivity> mController;
    private MainActivity mMainActivity;

    @Before
    public void setUp() {
        mController = Robolectric.buildActivity(MainActivity.class).create();
        Assert.assertNotNull(mController);
        mMainActivity = mController.get();
    }

    @Test
    public void testClick() throws Exception {
        TextView tvMain = mMainActivity.findView(R.id.tv_main);

        // 生命周期测试 -- 文本断言
        mController.resume();
        Assert.assertEquals("onResume", tvMain.getText().toString().trim());


        // 跳转测试
        Button btnStartActivity = mMainActivity.findView(R.id.btn_main);
        btnStartActivity.performClick();

        Intent expectedIntent = new Intent(mMainActivity, SecondActivity.class);
        Intent actualIntent = ShadowApplication.getInstance().getNextStartedActivity();
        Assert.assertEquals(expectedIntent, actualIntent);

        // dialog 测试
        mMainActivity.showCustomDialog();
        AlertDialog latestAlertDialog = ShadowAlertDialog.getLatestAlertDialog();
        Assert.assertNotNull(latestAlertDialog);

        // toast 测试
        mMainActivity.showToast("roboletric");
        String textOfLatestToast = ShadowToast.getTextOfLatestToast();
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
        Observable<List<Follower>> followers = apiService.getFollowers("lucid-lynxz");
        int size = followers.flatMap(Observable::from)
                .count()
                .toBlocking()
                .single();
        System.out.println(size);

        followers.toBlocking().forEach(followers1 -> System.out.println(followers1));
    }

    @Test
    public void testGetCallback() throws Exception {
        GithubApiService apiService = new GithubRetrofit().getApiService();
        Call<List<Follower>> followers = apiService.getFollowersCall("lucid-lynxz");
        // 立即执行,若写成 followers.enqueue(...) 则添加到队列后无法对结果进行处理
        Response<List<Follower>> execute = followers.execute();
        System.out.println(" callback " + execute.body());
    }


}