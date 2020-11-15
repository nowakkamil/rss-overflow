package com.example.android.services;

import com.example.android.common.models.Feed;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface StackOverflowService {
    @GET("/feeds")
    Observable<Feed> getFeed();
}
