package com.rssoverflow.services;

import com.rssoverflow.common.converters.DateConverter;
import com.rssoverflow.common.models.Feed;
import com.tickaroo.tikxml.TikXml;
import com.tickaroo.tikxml.converter.htmlescape.HtmlEscapeStringConverter;
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory;

import java.util.Date;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class StackOverflowClient {
    private static final String STACK_OVERFLOW_BASE_URL = "https://stackoverflow.com";

    private static StackOverflowClient instance;
    private StackOverflowService stackOverflowService;

    private StackOverflowClient() {
        final TikXml tikXml = new TikXml.Builder()
                .exceptionOnUnreadXml(false)
                .addTypeConverter(Date.class, new DateConverter())
                .addTypeConverter(String.class, new HtmlEscapeStringConverter())
                .build();

        final Retrofit retrofit = new Retrofit.Builder().baseUrl(STACK_OVERFLOW_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(TikXmlConverterFactory.create(tikXml))
                .build();

        stackOverflowService = retrofit.create(StackOverflowService.class);
    }

    public static StackOverflowClient getInstance() {
        if (instance == null) {
            instance = new StackOverflowClient();
        }

        return instance;
    }

    public Observable<Feed> getFeed() {
        return stackOverflowService.getFeed();
    }

    public Observable<Feed> getTag(String tag) {
        return stackOverflowService.getTag(tag);
    }
}
