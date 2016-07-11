package org.lynxz.robolectricdemo;

import android.app.AlertDialog;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.lynxz.robolectricdemo.model.Follower;
import org.lynxz.robolectricdemo.network.GithubApiService;
import org.lynxz.robolectricdemo.network.GithubRetrofit;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private TextView mTvMain;

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume ");
        mTvMain.setText("onResume");
        getGithubFollowers();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mTvMain.setText("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        mTvMain.setText("onStop");
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

        mTvMain = findView(R.id.tv_main);
        mTvMain.setText("onCreate");

        Button btnMain = findView(R.id.btn_main);
        Button btnDialog = findView(R.id.btn_main_dialog);
        Button btnToast = findView(R.id.btn_main_toast);
        btnMain.setOnClickListener(this);
        btnDialog.setOnClickListener(this);
        btnToast.setOnClickListener(this);
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_main:
                startActivity(new Intent(this, SecondActivity.class));
                break;
            case R.id.btn_main_dialog:
                showCustomDialog();
                break;
            case R.id.btn_main_toast:
                showToast("hello");
                break;
        }

    }

    public void showCustomDialog() {
        new AlertDialog.Builder(this).setTitle(R.string.hello).setMessage("world").show();

        // 使用下面自定义的dialog,在测试用用你总会报错:otFoundException: unknown resource ResName{org.lynxz.robolectricdemo:attr/null}
        //        Dialog loadingDialog = new Dialog(this, R.style.loading_dialog);
        //        View inflate = View.inflate(this, R.layout.layout_loading, null);
        //        loadingDialog.setContentView(inflate);
        //        loadingDialog.setCancelable(true);
        //        loadingDialog.show();
    }

    public void getGithubFollowers() {
        GithubApiService apiService = new GithubRetrofit().getApiService();
        Observable<List<Follower>> followers = apiService.getFollowers("lucid-lynxz");
        followers.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Follower>>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError ");
                    }

                    @Override
                    public void onNext(List<Follower> followers) {
                        Log.i(TAG, "onNext " + followers.size());
                    }
                });
        //        Call<List<Follower>> followers = apiService.getFollowers("lucid-lynxz");
        //        followers.enqueue(new Callback<List<Follower>>() {
        //            @Override
        //            public void onResponse(Call<List<Follower>> call, Response<List<Follower>> response) {
        //                Log.i(TAG, "onResponse " + response.body());
        //            }
        //
        //            @Override
        //            public void onFailure(Call<List<Follower>> call, Throwable t) {
        //                Log.i(TAG, "onFailure ");
        //            }
        //        });

    }
}