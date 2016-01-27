package com.thetonrifles.recyclerviewsample.twitter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thetonrifles.recyclerviewsample.R;

import java.util.List;

class ListTweetsAdapter extends RecyclerView.Adapter<ListTweetsAdapter.TweetViewHolder> {

    protected static class TweetViewHolder extends RecyclerView.ViewHolder {

        TextView content;

        public TweetViewHolder(View itemView) {
            super(itemView);
            content = (TextView) itemView.findViewById(R.id.txt_content);
        }

    }

    private LayoutInflater mLayoutInflater;
    private List<Tweet> mTweets;

    public ListTweetsAdapter(Context context, List<Tweet> tweets) {
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mTweets = tweets;
    }

    @Override
    public int getItemCount() {
        return mTweets.size();
    }

    @Override
    public TweetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.view_tweet_item, parent, false);
        return new TweetViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final TweetViewHolder viewHolder, final int position) {
        Tweet tweet = mTweets.get(position);
        viewHolder.content.setText(tweet.getContent());
    }

}
