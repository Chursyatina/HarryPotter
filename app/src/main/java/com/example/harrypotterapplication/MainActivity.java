package com.example.harrypotterapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import models.Character;
import models.Wand;

public class MainActivity extends AppCompatActivity implements CharactersAdapter.OnNoteListener {

    private BottomNavigationView bottomNavigationView;
    private androidx.recyclerview.widget.RecyclerView charactersRecyclerView;
    private CharactersAdapter charactersAdapter;
    private MainActivityViewModel mainActivityViewModel;
    private Gson gson = new Gson();
    private ArrayList<Character> characters = new ArrayList<Character>();
    private ArrayList<Wand> wands = new ArrayList<Wand>();
    private Map<String, Drawable> actorsPhotos = new HashMap<String, Drawable>();
    private Drawable loadingImage = null;
    public LifecycleOwner lifecycleOwner = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        charactersRecyclerView = findViewById(R.id.characters_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        charactersRecyclerView.setLayoutManager(layoutManager);
        charactersRecyclerView.setHasFixedSize(true);

        charactersAdapter = new CharactersAdapter(characters, actorsPhotos, loadingImage, this);
        charactersRecyclerView.setAdapter(charactersAdapter);

        bottomNavigationView = findViewById(R.id.main_navigation);

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        mainActivityViewModel.loadImage.observe(lifecycleOwner, new Observer<Drawable>() {
            @Override
            public void onChanged(Drawable drawable) {
                loadingImage = drawable;
                charactersAdapter.setLoadingImage(loadingImage);
            }
        });

        mainActivityViewModel.characters.observe(lifecycleOwner, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                characters = new ArrayList<Character>(Arrays.asList(new Gson().fromJson(s, Character[].class)));
                //wands = new ArrayList<Wand>(Arrays.asList(new Gson().fromJson(s, Wand[].class)));
                Log.i("myLogs", String.valueOf(characters));
                charactersAdapter.setCharacters(characters);
                mainActivityViewModel.LoadImages(characters);
            }
        });

        mainActivityViewModel.actorsPhotos.observe(lifecycleOwner, new Observer<Map<String, Drawable>>() {
            @Override
            public void onChanged(Map<String, Drawable> stringDrawableMap) {
                charactersAdapter.setActorsPhotos(stringDrawableMap);
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @org.jetbrains.annotations.NotNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.All:
                        charactersAdapter.setCharacters(characters);
                        break;
                    case R.id.Gryffindor:
                        charactersAdapter.setCharacters(new ArrayList<Character>((characters.stream().filter(character -> character.house.equals("Gryffindor")).collect(Collectors.toList()))));
                        break;
                    case R.id.Hufflepuff:
                        charactersAdapter.setCharacters(new ArrayList<Character>((characters.stream().filter(character -> character.house.equals("Hufflepuff"))).collect(Collectors.toList())));
                        break;
                    case R.id.Ravenclaw:
                        charactersAdapter.setCharacters(new ArrayList<Character>((characters.stream().filter(character -> character.house.equals("Ravenclaw"))).collect(Collectors.toList())));
                        break;
                    case R.id.Slytherin:
                        charactersAdapter.setCharacters(new ArrayList<Character>((characters.stream().filter(character -> character.house.equals("Slytherin"))).collect(Collectors.toList())));
                        break;
                }
                return true;
            }
        });
    }


    @Override
    public void onNoteClick(int position) {
        if (characters.get(position).wand.wood.length()>0 &&
                characters.get(position).wand.core.length()>0 &&
                characters.get(position).wand.length.length() > 0){

            Intent intent = new Intent(this, WandActivity.class);
            intent.putExtra("wood", String.valueOf(characters.get(position).wand.wood));
            intent.putExtra("core", String.valueOf(characters.get(position).wand.core));
            intent.putExtra("length", String.valueOf(characters.get(position).wand.length));
            startActivity(intent);
        }
    }
}