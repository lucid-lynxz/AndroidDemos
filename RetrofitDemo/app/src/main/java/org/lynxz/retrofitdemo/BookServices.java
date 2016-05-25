package org.lynxz.retrofitdemo;

import org.lynxz.retrofitdemo.bean.BookInfoBean;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by zxz on 2016/5/23.
 */
public interface BookServices {

    /**
     * 获取图书信息
     *
     * @param bookId
     * @return
     */
    @GET("/v2/book/{id}")
    Observable<BookInfoBean> getBookInfo(@Path("id") String bookId);

    @GET("/v2/book/{id}")
    Call<BookInfoBean> getBookInfo4Call(@Path("id") String bookId);

    /**
     * 获取输入流
     *
     * @param bookId
     * @return
     */
    @GET("/v2/book/{id}")
    Call<ResponseBody> getBookInfo4Response(@Path("id") String bookId);
    
}
