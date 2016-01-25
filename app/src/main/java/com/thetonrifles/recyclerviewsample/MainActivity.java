package com.thetonrifles.recyclerviewsample;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.thetonrifles.recyclerviewsample.adapter.ContactsAdapter;
import com.thetonrifles.recyclerviewsample.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private List<Contact> mContacts;
    private List<Contact> mFiltered;

    private ContactsAdapter mContactsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mContacts = buildContactsList();
        mFiltered = new ArrayList<>(mContacts);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.lst_items);
        recyclerView.setLayoutManager(getLayoutManager());
        mContactsAdapter = new ContactsAdapter(this, mContacts);
        recyclerView.setAdapter(mContactsAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextChange(String query) {
        mFiltered.clear();
        for (Contact contact : mContacts) {
            if (contact.getName().contains(query) || contact.getSurname().contains(query)) {
                mFiltered.add(contact);
            }
        }
        mContactsAdapter.updateContactsList(mFiltered);
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    private RecyclerView.LayoutManager getLayoutManager() {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        return llm;
    }

    private List<Contact> buildContactsList() {
        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("Giorgio", "Bianchi", "giorgio.bianchi@gmail.com"));
        contacts.add(new Contact("Mario", "Rossi", "mario.rossi@gmail.com"));
        contacts.add(new Contact("Giuseppe", "Verdi", "giuseppe.verdi@gmail.com"));
        return contacts;
    }

}
