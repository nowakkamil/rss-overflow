package com.example.android.services;

import com.example.android.common.models.Feed;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface StackOverflowService {
    @GET("/feeds")
    Observable<Feed> getFeed();

    @GET("/feeds/tag")
    Observable<Feed> getTag(@Query("tagnames") String tag);
}
