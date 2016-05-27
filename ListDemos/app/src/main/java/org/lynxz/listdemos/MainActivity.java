package org.lynxz.listdemos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_demo);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_gv_01, R.id.tv_gv_in_lv, R.id.tv_rv_basic, R.id.tv_item_anim_default, R.id.tv_item_load_more})
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.tv_gv_01:
                startActivity(new Intent(this, GridViewBasicActivity.class));
                break;
            case R.id.tv_gv_in_lv:
                startActivity(new Intent(this, GridViewInListViewActivity.class));
                break;
            case R.id.tv_rv_basic:
                startActivity(new Intent(this, RecyclerViewDemo.class));
                break;
            case R.id.tv_item_anim_default:
                startActivity(new Intent(this, RecycleViewAnimationActivity.class));
                break;
            case R.id.tv_item_load_more:
                startActivity(new Intent(this, LoadMoreRVActivity.class));
                break;
        }
    }
}
