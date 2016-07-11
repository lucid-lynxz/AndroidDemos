package org.lynxz.robolectricdemo;

import android.widget.EditText;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

/**
 * Created by zxz on 2016/7/11.
 */
//@RunWith(RobolectricTestRunner.class)
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class SecondActivityTest {

    private EditText mEdt;
    private SecondActivity mSecondActivity;

    @Before
    public void setUp() throws Exception {
        ActivityController<SecondActivity> controller = Robolectric.buildActivity(SecondActivity.class).create().start();
        mSecondActivity = controller.get();
        mEdt = mSecondActivity.findView(R.id.edt_second);
    }

    @Test
    public void testSetTvText() throws Exception {
        String msg = "xxx";
        mSecondActivity.setTvText(msg);
        Assert.assertEquals(msg, mEdt.getText().toString().trim());
    }
}