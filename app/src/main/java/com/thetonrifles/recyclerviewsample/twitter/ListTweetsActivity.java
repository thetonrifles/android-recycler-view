package com.thetonrifles.recyclerviewsample.twitter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.thetonrifles.recyclerviewsample.R;
import com.thetonrifles.recyclerviewsample.twitter.rest.TwitterRestClient;

import java.util.ArrayList;
import java.util.List;

public class ListTweetsActivity extends AppCompatActivity {

    private static final String LOG_TAG = ListTweetsActivity.class.getSimpleName();

    private TwitterRestClient mHttp;
    private List<Tweet> mTweets;
    private ListTweetsAdapter mTweetsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweets_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTweets = new ArrayList<>();
        mHttp = new TwitterRestClient(this);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.lst_items);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mTweetsAdapter = new ListTweetsAdapter(this, mTweets);
        recyclerView.setAdapter(mTweetsAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadFeeds();
    }

    private void loadFeeds() {
//        mHttp.getToken(new HttpPostRequestListener() {
//            @Override
//            public void onSuccess(String result) {
//                Log.d("test", result);
//            }
//
//            @Override
//            public void onFailure(Exception ex) {
//                Log.e("test", ex.getMessage());
//            }
//        });
    }

}
