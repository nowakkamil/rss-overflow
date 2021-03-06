package com.rssoverflow;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rssoverflow.common.activities.SampleActivityBase;
import com.rssoverflow.common.logger.Log;
import com.rssoverflow.common.logger.LogWrapper;
import com.rssoverflow.common.logger.MessageOnlyLogFilter;
import com.rssoverflow.common.models.Entry;
import com.rssoverflow.common.models.Feed;
import com.rssoverflow.recyclerview.R;
import com.rssoverflow.recyclerview.RecyclerViewFragment;
import com.rssoverflow.services.StackOverflowClient;

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

    /**
     * Clear focus on touch outside of EditText
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }

        return super.dispatchTouchEvent(event);
    }

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

        final EditText editTag = (EditText) findViewById(R.id.edit_tag);
        final Button buttonSearch = (Button) findViewById(R.id.button_search);
        buttonSearch.setOnClickListener(v -> searchTag(editTag));

        editTag.setOnEditorActionListener((textView, actionId, keyEvent) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchTag(editTag);
                return true;
            }

            return false;
        });
    }

    public void createInitialObservable() {
        StackOverflowClient.getInstance()
                .getFeed()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());
    }

    private void createObservable(String tag) {
        StackOverflowClient.getInstance()
                .getTag(tag)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());
    }

    private void searchTag(EditText editTag) {
        final String tag = editTag.getText().toString();
        if (TextUtils.isEmpty(tag)) {
            return;
        }

        createObservable(tag);
        hideKeyboard(editTag);
    }

    private void hideKeyboard(EditText editTag) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editTag.getWindowToken(), 0);
    }

    /**
     * Observer
     * Handles the stream of data
     */
    private Observer<Feed> getObserver() {
        return new Observer<Feed>() {
            @Override
            public void onComplete() {
                Log.d(TAG, "In onComplete()");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "In onError()");
                e.printStackTrace();

                Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSubscribe(Disposable d) {
            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onNext(Feed feed) {
                Log.d(TAG, "In onNext()");

                mFragment.scrollToTop();

                Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();

                Log.i(TAG, "feed title: " + feed.getTitle());
                Log.i(TAG, "feed updated: " + feed.getUpdated());

                List<Entry> entries = feed.getEntries();
                if (entries == null) {
                    return;
                }
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
