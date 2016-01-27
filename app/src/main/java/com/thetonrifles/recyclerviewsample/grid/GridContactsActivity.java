package com.thetonrifles.recyclerviewsample.grid;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.thetonrifles.recyclerviewsample.Contacts;
import com.thetonrifles.recyclerviewsample.R;
import com.thetonrifles.recyclerviewsample.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class GridContactsActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private List<Contact> mContacts;
    private List<Contact> mFiltered;

    private RecyclerView mRecyclerView;
    private GridContactsAdapter mContactsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mContacts = Contacts.getAll();
        mFiltered = new ArrayList<>(mContacts);

        mRecyclerView = (RecyclerView) findViewById(R.id.lst_items);
        mRecyclerView.setLayoutManager(getDefaultLayoutManager());
        mContactsAdapter = new GridContactsAdapter(this, mFiltered);
        mRecyclerView.setAdapter(mContactsAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_contacts, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextChange(String query) {
        mFiltered.clear();
        String lowerQuery = query.toLowerCase();
        for (Contact contact : mContacts) {
            if (TextUtils.isEmpty(lowerQuery) ||
                    contact.getName().toLowerCase().contains(lowerQuery) ||
                    contact.getSurname().toLowerCase().contains(lowerQuery)) {
                mFiltered.add(contact);
            }
        }
        if (mFiltered.size() == 0) {
            mRecyclerView.setLayoutManager(getEmptyLayoutManager());
        } else {
            mRecyclerView.setLayoutManager(getDefaultLayoutManager());
        }
        mContactsAdapter.notifyDataSetChanged();
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    private GridLayoutManager getDefaultLayoutManager() {
        return new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
    }

    private RecyclerView.LayoutManager getEmptyLayoutManager() {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        return llm;
    }

}
