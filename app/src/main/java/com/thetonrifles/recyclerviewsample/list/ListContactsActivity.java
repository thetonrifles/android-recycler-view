package com.thetonrifles.recyclerviewsample.list;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.thetonrifles.recyclerviewsample.R;
import com.thetonrifles.recyclerviewsample.Utils;

import java.util.ArrayList;
import java.util.List;

public class ListContactsActivity extends AppCompatActivity {

    private List<Contact> mContacts;

    private ListContactsAdapter mContactsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mContacts = new ArrayList<>();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.lst_items);
        recyclerView.setLayoutManager(getLayoutManager());
        mContactsAdapter = new ListContactsAdapter(this, mContacts);
        mContactsAdapter.setOnItemTapListener(new ListContactsAdapter.OnItemTapListener() {
            @Override
            public void onItemTap(Contact contact) {
                mContacts.remove(contact);
                mContactsAdapter.updateContactsList();
            }
        });
        recyclerView.setAdapter(mContactsAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContacts.add(new Contact(
                        Utils.buildRandomString(5),
                        Utils.buildRandomString(5)));
                mContactsAdapter.updateContactsList();
            }
        });
    }

    private RecyclerView.LayoutManager getLayoutManager() {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        return llm;
    }

}
