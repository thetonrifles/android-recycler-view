package com.thetonrifles.recyclerviewsample.listview;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.thetonrifles.recyclerviewsample.R;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private List<String> mItems;
    private ArrayAdapter<String> mItemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        mItems = new ArrayList<>();

        ListView listView = (ListView) findViewById(R.id.lst_items);
        mItemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mItems);
        listView.setAdapter(mItemsAdapter);

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

