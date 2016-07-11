package org.lynxz.robolectricdemo.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by devinshine on 15/9/4.
 * retrofit 实例
 */
public class GithubRetrofit {
    String mServerUrl = "https://api.github.com";

    final static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .serializeNulls()
            .create();

    Interceptor interceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request().newBuilder()
                    .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0")
                    .build();
            return chain.proceed(request);
        }
    };

    OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(5000, TimeUnit.MILLISECONDS)
            .readTimeout(5000, TimeUnit.MILLISECONDS)
            .addInterceptor(interceptor)
            .build();

    private GithubApiService mGithubApiService;


    public GithubApiService getApiService() {
        if (mGithubApiService == null) {
            Retrofit mRetrofit = new Retrofit.Builder()
                    .baseUrl(mServerUrl) // 服务器地址
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(client)
                    .build();

            mGithubApiService = mRetrofit.create(GithubApiService.class);
        }
        return mGithubApiService;
    }
}
