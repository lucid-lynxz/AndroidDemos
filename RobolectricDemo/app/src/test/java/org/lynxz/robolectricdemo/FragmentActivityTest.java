package org.lynxz.robolectricdemo;

import android.support.v4.app.Fragment;
import android.widget.TextView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lynxz.robolectricdemo.fragment.FragmentDemo;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import java.util.List;

/**
 * Created by Lynxz on 2016/7/12.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class FragmentActivityTest {


    private ActivityController<FragmentActivity> mController;
    private FragmentActivity mFragmentActivity;

    @Before
    public void setUp() throws Exception {
        mController = Robolectric.buildActivity(FragmentActivity.class).create().start().visible();
        Assert.assertNotNull(mController);
        mFragmentActivity = mController.get();

    }

    @Test
    public void testReplaceFragment() throws Exception {
        FragmentDemo fragmentDemo = new FragmentDemo();
        //        SupportFragmentTestUtil.startFragment(fragmentDemo);
        Assert.assertNotNull(fragmentDemo.getView());

        int commit = mFragmentActivity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.rl_container, fragmentDemo).commit();
        System.out.println("commit :  " + commit);
    }

    @Test
    public void testLoadData() throws Exception {
        int count = 0;
        List<Fragment> fragments = mFragmentActivity.getSupportFragmentManager().getFragments();
        Assert.assertNotNull(fragments);
        System.out.println("fragments.size() = " + fragments.size());
        FragmentDemo fragment = (FragmentDemo) fragments.get(0);
        TextView tvTip = fragment.findView(R.id.tv_frag);
        fragment.loadData();
        count++;
        Assert.assertEquals(count + "", tvTip.getText().toString());
    }
}