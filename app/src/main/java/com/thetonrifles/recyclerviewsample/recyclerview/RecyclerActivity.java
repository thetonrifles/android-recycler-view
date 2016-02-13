package com.thetonrifles.recyclerviewsample.recyclerview;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.thetonrifles.recyclerviewsample.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerActivity extends AppCompatActivity {

    private List<String> mItems;
    private RecyclerAdapter mItemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        mItems = new ArrayList<>();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.lst_items);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mItemsAdapter = new RecyclerAdapter(this, mItems);
        recyclerView.setAdapter(mItemsAdapter);

        (new MyAsyncTask()).execute();
    }

    private class MyAsyncTask extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... params) {
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            List<String> items = new ArrayList<>();
            items.add("Radja");
            items.add("Miralem");
            items.add("Kevin");
            mItems.clear();
            mItems.addAll(items);
            mItemsAdapter.notifyDataSetChanged();
        }

    }

}
