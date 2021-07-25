package com.example.harrypotterapplication;

import android.app.Application;
import android.graphics.drawable.Drawable;
import android.provider.ContactsContract;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import externalData.DataLoader;
import models.Character;

public class MainActivityViewModel extends AndroidViewModel {

    private static final int NUMBER_OF_THREADS = 8;
    public static final ExecutorService executor =
            Executors.newScheduledThreadPool(NUMBER_OF_THREADS);

    private DataLoader dataLoader;
    public MutableLiveData<Drawable> loadImage;
    public MutableLiveData<String> characters;
    public MutableLiveData<Map<String, Drawable>> actorsPhotos;

    public MainActivityViewModel(@NonNull @NotNull Application application) {
        super(application);

        characters = new MutableLiveData<>();
        actorsPhotos = new MutableLiveData<>();
        loadImage = new MutableLiveData<>();

        try {
            dataLoader = new DataLoader();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        executor.execute(() ->
        {
            try {
                loadImage.postValue(dataLoader._loadLoadingImage());
                characters.postValue(dataLoader._loadData());
            }
            catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    public void LoadImages (ArrayList<Character> characters){
        try {
            dataLoader = new DataLoader();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        executor.execute(() ->
        {
            try {
                actorsPhotos.postValue(dataLoader._loadImages(characters));
            }
            catch (Exception e){
                e.printStackTrace();
            }
        });
    }
}
