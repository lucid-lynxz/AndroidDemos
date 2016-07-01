package org.lynxz.robotiumdemoapp;

import android.test.ActivityInstrumentationTestCase2;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;

import com.robotium.solo.Solo;

// 手动添加继承关系到 `ActivityInstrumentationTestCase2`
public class LoginActivityTest extends ActivityInstrumentationTestCase2<LoginActivity> {


    private Solo mSolo;
    private LoginActivity mActivity;

    // 需要手动添加无参数的构造函数,不然报错: AssertionFailedError: <class> has no public constructor
    public LoginActivityTest() {
        super(LoginActivity.class);
    }


    //　重写setup()方法,初始化Solo对象
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
        // 查找控件
        final EditText edtMail = (EditText) mSolo.getView(R.id.email);
        final EditText edtPwd = (EditText) mSolo.getView(R.id.password);
        Button btn = (Button) mSolo.getView(R.id.email_sign_in_button);

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

        mSolo.sleep(3000);

        // 清空文本
        mSolo.clearEditText(edtMail);
        mSolo.clearEditText(edtPwd);
        mSolo.sleep(1000);

        // 输入文本内容
        mSolo.enterText(edtMail, "hello_world@zzz.com");
        mSolo.enterText(edtPwd, "123456");

        // clickOnButton提示我找不到...郁闷,还是clickOnView比较好用
        // mSolo.clickOnButton(R.id.email_sign_in_button);
        // mSolo.clickOnText("Sign in or register");

        mSolo.sleep(3000);

        // 单击按钮,跳转到MainActivity
        mSolo.clickOnView(btn);

        // 截屏操作,文件存放在 /sdcard/Robotium-Screenshots/ 中
        mSolo.takeScreenshot();

        mSolo.sleep(3000);

        // 这里可以直接操作第二个页面的内容
        mSolo.clickOnView(mSolo.getView(R.id.btn_hello));

        mSolo.sleep(5000);
        // 完成测试用例,应用退出
    }
}