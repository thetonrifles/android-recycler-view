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

import java.util.List;

public class SortPlayersActivity extends AppCompatActivity {

    private List<Player> mPlayers;

    private SortPlayersAdapter mPlayersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mPlayers = Players.getAll();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.lst_items);
        recyclerView.setLayoutManager(getLayoutManager());
        mPlayersAdapter = new SortPlayersAdapter(this, mPlayers);
        recyclerView.setAdapter(mPlayersAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlayers.add(new Player(
                        Utils.buildRandomInt(10),
                        Utils.buildRandomName(5) + " " + Utils.buildRandomName(5)));
                mPlayersAdapter.updatePlayersList();
            }
        });
    }

    private RecyclerView.LayoutManager getLayoutManager() {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        return llm;
    }

}
