package com.example.harrypotterapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class WandActivity extends AppCompatActivity {

    public TextView woodTextView;
    public TextView coreTextView;
    public TextView lengthTextView;

    public String wood;
    public String core;
    public String length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wand);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        woodTextView = findViewById(R.id.wood_value_tv);
        coreTextView = findViewById(R.id.core_value_tv);
        lengthTextView = findViewById(R.id.length_value_tv);

        Intent intent = getIntent();
        wood = intent.getStringExtra("wood");
        core = intent.getStringExtra("core");
        length = intent.getStringExtra("length");

        woodTextView.setText(wood);
        coreTextView.setText(core);
        lengthTextView.setText(length);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}