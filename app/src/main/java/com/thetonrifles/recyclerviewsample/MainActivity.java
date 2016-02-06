package com.thetonrifles.recyclerviewsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        List<Contact> contacts = getAll();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.lst_items);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ContactsAdapter adapter = new ContactsAdapter(this, contacts);
        recyclerView.setAdapter(adapter);
    }

    private ArrayList<Contact> getAll() {
        ArrayList<Contact> contacts = new ArrayList<>();
//        contacts.add(new Contact("Giorgio", "Bianchi"));
//        contacts.add(new Contact("Mario", "Rossi"));
//        contacts.add(new Contact("Giuseppe", "Verdi"));
//        contacts.add(new Contact("Marco", "Gialli"));
//        contacts.add(new Contact("Andrea", "Mainardi"));
//        contacts.add(new Contact("Rocco", "Tano"));
        return contacts;
    }

}
