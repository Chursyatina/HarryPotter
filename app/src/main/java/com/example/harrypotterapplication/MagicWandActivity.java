package com.example.harrypotterapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class MagicWandActivity extends AppCompatActivity {

    public TextView woodTextView;
    public TextView coreTextView;
    public TextView lengthTextView;

    public String wood;
    public String core;
    public String length;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.magic_wand_layout);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        woodTextView.findViewById(R.id.wood_value_tv);
        coreTextView.findViewById(R.id.core_value_tv);
        lengthTextView.findViewById(R.id.length_value_tv);

        Intent intent = getIntent();
        wood = intent.getStringExtra("wood");
        core = intent.getStringExtra("core");
        length = intent.getStringExtra("length");

        woodTextView.setText(wood);
        coreTextView.setText(core);
        lengthTextView.setText(length);
    }
}
