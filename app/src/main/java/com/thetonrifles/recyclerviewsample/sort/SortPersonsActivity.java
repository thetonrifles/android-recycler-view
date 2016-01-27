package com.thetonrifles.recyclerviewsample.sort;

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

public class SortPersonsActivity extends AppCompatActivity {

    private List<Person> mPersons;

    private SortPersonsAdapter mPlayersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mPersons = getAll();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.lst_items);
        recyclerView.setLayoutManager(getLayoutManager());
        mPlayersAdapter = new SortPersonsAdapter(this, mPersons);
        recyclerView.setAdapter(mPlayersAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // building new fake player
                Person person = new Person(
                        Utils.buildRandomInt(10),
                        Utils.buildRandomName(5) + " " + Utils.buildRandomName(5));
                // let's keep also basic list updated
                mPersons.add(person);
                // let's update adapter
                mPlayersAdapter.addPlayer(person);
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
        persons.add(new Person(1, "James Kub"));
        persons.add(new Person(2, "Peter Hanly"));
        persons.add(new Person(3, "Josh Penny"));
        persons.add(new Person(1, "Danny Jackson"));
        persons.add(new Person(3, "Brad Black"));
        return persons;
    }

}