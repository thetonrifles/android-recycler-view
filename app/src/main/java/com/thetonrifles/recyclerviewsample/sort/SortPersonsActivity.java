package com.thetonrifles.recyclerviewsample.sort;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.thetonrifles.recyclerviewsample.R;
import com.thetonrifles.recyclerviewsample.Utils;

import java.util.ArrayList;
import java.util.List;

public class SortPersonsActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private String mSearchQuery;

    private List<Person> mPersons;

    private SortPersonsAdapter mPersonsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persons_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mPersons = getAll();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.lst_items);
        recyclerView.setLayoutManager(getLayoutManager());
        mPersonsAdapter = new SortPersonsAdapter(this, mPersons);
        recyclerView.setAdapter(mPersonsAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // building new fake person
                Person person = new Person(
                        Utils.buildRandomInt(10) + "\nrank",
                        Utils.buildRandomName(5) + " " + Utils.buildRandomName(5));
                // let's keep also basic list updated
                mPersons.add(person);
                if (matchesFilter(person, mSearchQuery)) {
                    // let's update adapter
                    mPersonsAdapter.addPerson(person);
                }
            }
        });
    }

    private RecyclerView.LayoutManager getLayoutManager() {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        return llm;
    }

    private List<Person> getAll() {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("1\nrank", "James Kub"));
        persons.add(new Person("2\nrank", "Peter Hanly"));
        persons.add(new Person("3\nrank", "Josh Penny"));
        persons.add(new Person("1\nrank", "Danny Jackson"));
        persons.add(new Person("3\nrank", "Brad Black"));
        return persons;
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
        mSearchQuery = query;
        mPersonsAdapter.removePersons();
        for (Person person : mPersons) {
            if (matchesFilter(person, query)) {
                mPersonsAdapter.addPerson(person);
            } else {
                mPersonsAdapter.removePerson(person);
            }
        }
        mPersonsAdapter.notifyDataSetChanged();
        return false;
    }

    private boolean matchesFilter(Person contact, String query) {
        String lowerQuery = (query!= null) ? query.toLowerCase() : "";
        return TextUtils.isEmpty(lowerQuery) ||
                contact.getName().toLowerCase().contains(lowerQuery);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

}
