package com.thetonrifles.recyclerviewsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.thetonrifles.recyclerviewsample.adapter.ContactsAdapter;
import com.thetonrifles.recyclerviewsample.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class SolutionTwoActivity extends AppCompatActivity {

    private List<Contact> mContacts;
    private List<Contact> mFiltered;

    private ContactsAdapter mContactsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mContacts = buildContactsList();
        mFiltered = new ArrayList<>(mContacts);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.lst_items);
        recyclerView.setLayoutManager(getLayoutManager());
        mContactsAdapter = new ContactsAdapter(this, mContacts);
        recyclerView.setAdapter(mContactsAdapter);
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
