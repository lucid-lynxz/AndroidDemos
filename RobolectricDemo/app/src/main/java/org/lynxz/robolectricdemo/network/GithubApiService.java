package org.lynxz.robolectricdemo.network;

import org.lynxz.robolectricdemo.model.Follower;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Lynxz on 2016/7/11.
 */
public interface GithubApiService {

    @GET("/users/{username}/followers")
    Observable<List<Follower>> getFollowers(@Path("username") String userName);


    @GET("/users/{username}/followers")
    Call<List<Follower>> getFollowersCall(@Path("username") String userName);
}
