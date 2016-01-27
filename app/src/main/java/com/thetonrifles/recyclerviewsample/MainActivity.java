package com.thetonrifles.recyclerviewsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.thetonrifles.recyclerviewsample.list.ListContactsActivity;
import com.thetonrifles.recyclerviewsample.grid.GridContactsActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View one = findViewById(R.id.btn_solution_one);
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListContactsActivity.class);
                startActivity(intent);
            }
        });

        View two = findViewById(R.id.btn_solution_two);
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GridContactsActivity.class);
                startActivity(intent);
            }
        });
    }

}
