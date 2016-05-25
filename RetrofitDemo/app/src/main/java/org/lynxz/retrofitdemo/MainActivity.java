package org.lynxz.retrofitdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.lynxz.retrofitdemo.bean.BookInfoBean;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity {

    private static final String SERVER_URL = "https://api.douban.com";
    private static final String bookId = "1220562";
    private static final String TAG = "MainActivity";
    private BookServices mBookServices;
    private Observable<BookInfoBean> mBookInfo;

    @Bind(R.id.tv_content)
    TextView mTvContent;
    private Subscription mGetBookInfoSubscribe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initRetrofit();
    }

    private void initRetrofit() {
        // 统一添加head
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder()
                        .addHeader("headKey", "headValue")
                        .build();
                return chain.proceed(request);
            }
        };


        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                .readTimeout(5000, TimeUnit.MILLISECONDS)
                .addInterceptor(interceptor)
                .build();
        // retrofit2中的interceptors()是个不可变的list,不能使用add方法
        //        client.interceptors().add(null);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                // 将Call转换成RxJava observable时需要
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();

        mBookServices = retrofit.create(BookServices.class);
    }

    @OnClick({R.id.btn_get_book_info})
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get_book_info:
                getBookInfo(bookId);
                break;
        }
    }

    private void getBookInfo(final String bookId) {

        //使用Call回调,enqueue:异步
        //        final Call<BookInfoBean> call = mBookServices.getBookInfo4Call(bookId);
        //        call.enqueue(new Callback<BookInfoBean>() {
        //            @Override
        //            public void onResponse(Call<BookInfoBean> call, retrofit2.Response<BookInfoBean> response) {
        //                mTvContent.setText(response.body().toString());
        //            }
        //
        //            @Override
        //            public void onFailure(Call<BookInfoBean> call, Throwable t) {
        //                mTvContent.setText(t.getMessage());
        //            }
        //        });


        // 使用Call回调返回response,获取输入流
        // 使用场景: 请求图片,服务端返回图片流,则获取流以后通过BitmapFactory.decodeStream(in)解析即可
        //        Call<ResponseBody> bookInfo4Response = mBookServices.getBookInfo4Response(bookId);
        //        bookInfo4Response.enqueue(new Callback<ResponseBody>() {
        //            @Override
        //            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
        //                try {
        //                    int statusCode = response.code();
        //                    if (response.isSuccess()) {//直接判断是否返回成功,也可通过状态码判断
        //                        InputStream inputStream = response.body().byteStream();
        //                        String responseStr = ConvertUtil.inputStreamToString(inputStream);
        //                        Log.i(TAG, "run code: " + statusCode + " , result: " + responseStr);
        //                        mTvContent.setText(responseStr);
        //                    } else {
        //                        ResponseBody errorBody = response.errorBody();
        //                        mTvContent.setText("request failed code: " + statusCode + " msg: " + errorBody.string());
        //                    }
        //                } catch (IOException e) {
        //                    e.printStackTrace();
        //                }
        //            }
        //
        //            @Override
        //            public void onFailure(Call<ResponseBody> call, Throwable t) {
        //                mTvContent.setText(t.getMessage());
        //            }
        //        });

        // 取消Call请求,对于Observable的话,则直接解绑订阅即可
        //        bookInfo4Response.cancel();


        // 使用callAdapter返回Observable并将json解析成对应的实体类
        mBookInfo = mBookServices.getBookInfo(bookId);
        mGetBookInfoSubscribe = mBookInfo.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BookInfoBean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(BookInfoBean bookInfoBean) {
                        mTvContent.setText(bookInfoBean.toString());
                    }
                });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        if (!mGetBookInfoSubscribe.isUnsubscribed()) {
            mGetBookInfoSubscribe.unsubscribe();
        }
    }
}
