package org.lynxz.rjobotiumdemotest;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;

import com.robotium.solo.Solo;

/**
 * 黑盒测试
 * 运行时,通过Terminal来生成apk并安装和启动testRunner:
 *      gradlew clean
 *      gradlew assembleDebugAndroidTest # 生成test的apk包
 *      adb install -r app/build/outputs/apk/app-debug-androidTest-unaligned.apk # 安装测试用例包
 *      adb shell am instrument -w your_pkg_name.test/android.test.InstrumentationTestRunner # 运行
 */
public class LoginActivityTest extends ActivityInstrumentationTestCase2 {

    public static final String PKG_NAME = "org.lynxz.robotiumdemoapp";
    public static final String ACTIVITY_NAME = "org.lynxz.robotiumdemoapp.LoginActivity";

    private Solo mSolo;
    private Activity mActivity;

    // 由于无源码,这里使用反射来加载页面
    public LoginActivityTest() throws ClassNotFoundException {
        super(Class.forName(ACTIVITY_NAME));
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mActivity = getActivity();
        mSolo = new Solo(getInstrumentation(), mActivity);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        //        mSolo.finishOpenedActivities();
    }

    // 实际的测试内容
    public void testAutoLogin() throws Exception {
        // 查找控件,以下两种方式都可以,这里不能直接使用 'R.id.**' 来查找
        final EditText edtMail = (EditText) mSolo.getView("email");
        final EditText edtPwd = (EditText) mSolo.getView(PKG_NAME + ":id/password");
        final Button btn = (Button) mSolo.getView(PKG_NAME + ":id/email_sign_in_button");

        // requestFocus() 是操作界面上的控件,需要运行在待应用的线程中,而非测试用例的线程中
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                edtPwd.requestFocus();
            }
        });
        // 发送按键
        sendKeys(KeyEvent.KEYCODE_D, KeyEvent.KEYCODE_3);

        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                edtMail.requestFocus();
            }
        });
        sendKeys(KeyEvent.KEYCODE_3);

        mSolo.sleep(1500);

        // 清空文本
        mSolo.clearEditText(edtMail);
        mSolo.clearEditText(edtPwd);
        mSolo.sleep(1000);

        // 输入文本内容
        mSolo.enterText(edtMail, "hello_world@zzz.com");
        mSolo.enterText(edtPwd, "123456");

        mSolo.sleep(1500);

        // 单击按钮,跳转到MainActivity
        mSolo.clickOnView(btn);

        // 截屏操作,文件存放在 /sdcard/Robotium-Screenshots/ 中
        mSolo.takeScreenshot();

        mSolo.sleep(1500);

        // 这里可以直接操作第二个页面的内容,黑盒这里使用文本找不到Button控件 ==!
        //        mSolo.clickOnView(mSolo.getView("HI"));
        mSolo.clickOnView(mSolo.getView(PKG_NAME + ":id/btn_hello"));

        mSolo.sleep(5000);
        // 完成测试用例,应用退出
    }
}