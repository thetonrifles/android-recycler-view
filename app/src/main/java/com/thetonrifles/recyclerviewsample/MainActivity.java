package com.thetonrifles.recyclerviewsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.thetonrifles.recyclerviewsample.list.ListContactsActivity;
import com.thetonrifles.recyclerviewsample.grid.GridContactsActivity;
import com.thetonrifles.recyclerviewsample.sort.SortPersonsActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View list = findViewById(R.id.btn_list_layout);
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListContactsActivity.class);
                startActivity(intent);
            }
        });

        View grid = findViewById(R.id.btn_grid_layout);
        grid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GridContactsActivity.class);
                startActivity(intent);
            }
        });

        View sort = findViewById(R.id.btn_sorted_list);
        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SortPersonsActivity.class);
                startActivity(intent);
            }
        });
    }

}
