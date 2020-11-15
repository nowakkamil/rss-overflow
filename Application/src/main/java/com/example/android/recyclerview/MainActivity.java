package com.example.android.recyclerview;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;

import com.example.android.common.activities.SampleActivityBase;
import com.example.android.common.logger.Log;
import com.example.android.common.logger.LogWrapper;
import com.example.android.common.logger.MessageOnlyLogFilter;
import com.example.android.common.models.Entry;
import com.example.android.common.models.Feed;
import com.example.android.services.StackOverflowClient;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple launcher activity a custom {@link android.support.v4.app.Fragment} which can display a view.
 */
public class MainActivity extends SampleActivityBase {

    public static final String TAG = "MainActivity";

    private RecyclerViewFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            mFragment = new RecyclerViewFragment();
            transaction.replace(R.id.sample_content_fragment, mFragment);
            transaction.commit();
        }

        createObservable();
    }

    private void createObservable() {
        StackOverflowClient.getInstance()
                .getFeed()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getDisposableObserver());
    }

    /**
     * Observer
     * Handles the stream of data
     */
    private Observer<Feed> getDisposableObserver() {
        return new Observer<Feed>() {
            @Override
            public void onComplete() {
                Log.d(TAG, "In onComplete()");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                Log.d(TAG, "In onError()");
            }

            @Override
            public void onSubscribe(Disposable d) {
            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onNext(Feed feed) {
                Log.d(TAG, "In onNext()");

                Log.i(TAG, "feed title: " + feed.getTitle());
                Log.i(TAG, "feed updated: " + feed.getUpdated());

                List<Entry> entries = feed.getEntries();
                entries.forEach(System.out::println);

                mFragment.updateEntries(entries);
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * Create a chain of targets that will receive log data
     */
    @Override
    public void initializeLogging() {
        // Wraps Android's native log framework.
        LogWrapper logWrapper = new LogWrapper();
        // Using Log, front-end to the logging chain, emulates android.util.log method signatures.
        Log.setLogNode(logWrapper);

        // Filter strips out everything except the message text.
        MessageOnlyLogFilter msgFilter = new MessageOnlyLogFilter();
        logWrapper.setNext(msgFilter);

        Log.i(TAG, "Ready");
    }
}
