package org.lynxz.robotiumdemoapp;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;

import com.robotium.solo.Solo;

// 手动添加继承关系到 `ActivityInstrumentationTestCase2`
public class LoginActivityTest extends ActivityInstrumentationTestCase2<LoginActivity> {


    private Solo mSolo;

    // 需要手动添加无参数的构造函数,不然报错: AssertionFailedError: <class> has no public constructor
    public LoginActivityTest() {
        super(LoginActivity.class);
    }


    //　重写setup()方法,初始化Solo对象
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mSolo = new Solo(getInstrumentation(), getActivity());
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        //        mSolo.finishOpenedActivities();
    }

    // 实际的测试内容
    public void testAutoLogin() throws Exception {
        // 查找控件
        EditText edtMail = (EditText) mSolo.getView(R.id.email);
        EditText edtPwd = (EditText) mSolo.getView(R.id.password);
        Button btn = (Button) mSolo.getView(R.id.email_sign_in_button);

        // 输入文本内容
        mSolo.enterText(edtMail, "lucid_lynxz@163.com");
        mSolo.enterText(edtPwd, "123456");
        // clickOnButton提示我找不到...郁闷,还是clickOnView比较好用
        // mSolo.clickOnButton(R.id.email_sign_in_button);
        // mSolo.clickOnText("Sign in or register");

        // 清空文本内容
        // mSolo.clearEditText(edtMail);

        mSolo.sleep(3000);
        mSolo.clickOnView(btn);
        mSolo.takeScreenshot();
        mSolo.sleep(3000);
    }
}