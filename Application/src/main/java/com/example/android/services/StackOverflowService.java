package com.example.android.services;

import com.example.android.common.models.Feed;

import retrofit2.http.GET;
import rx.Observable;

public interface StackOverflowService {
    @GET("/feeds")
    Observable<Feed> getFeed();
}
