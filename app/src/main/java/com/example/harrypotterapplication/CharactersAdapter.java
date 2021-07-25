 package com.example.harrypotterapplication;

import android.content.Context;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.xml.sax.DTDHandler;

import java.io.Console;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

import models.Character;

public class CharactersAdapter extends RecyclerView.Adapter<CharactersAdapter.CharacterViewHolder> {

    private ArrayList<Character> characters;
    private Map<String, Drawable> actorsPhotos;
    private Drawable loadingImage;
    private OnNoteListener mOnNoteListener;

    public CharactersAdapter(ArrayList<Character> characters, Map<String, Drawable> actorsPhotos, Drawable loadingImage, OnNoteListener onNoteListener){
        this.characters = characters;
        this.actorsPhotos = actorsPhotos;
        this.loadingImage = loadingImage;
        this.mOnNoteListener = onNoteListener;
    }

    public void setCharacters(ArrayList<Character> characters) {
        this.characters = characters;
        this.notifyDataSetChanged();
    }

    public void setActorsPhotos(Map<String, Drawable> drawables) {
        this.actorsPhotos = drawables;
        this.notifyDataSetChanged();
    }

    public void setLoadingImage(Drawable loadingImage) {
        this.loadingImage = loadingImage;
        this.notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.character_list_item;

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem, parent, false);

        CharacterViewHolder viewHolder = new CharacterViewHolder(view, mOnNoteListener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CharactersAdapter.CharacterViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return characters.size();
    }

    class CharacterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView actorPhoto;
        TextView characterName;
        TextView characterSpecies;
        TextView characterGender;
        TextView characterHouse;
        TextView characterDateOfBirth;
        TextView characterAncestry;
        TextView characterEyeColour;
        TextView characterHairColour;
        TextView characterPatronus;
        OnNoteListener onNoteListener;

        public CharacterViewHolder(@NonNull @NotNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);

            actorPhoto = itemView.findViewById(R.id.actor_photo);
            characterName = itemView.findViewById(R.id.character_name);
            characterSpecies = itemView.findViewById(R.id.character_species);
            characterGender = itemView.findViewById(R.id.character_Gender);
            characterHouse = itemView.findViewById(R.id.character_House);
            characterDateOfBirth = itemView.findViewById(R.id.character_DateOfBirth);
            characterAncestry = itemView.findViewById(R.id.character_Ancestry);
            characterEyeColour = itemView.findViewById(R.id.character_EyeColour);
            characterHairColour = itemView.findViewById(R.id.character_HairColour);
            characterPatronus = itemView.findViewById(R.id.character_Patronus);

            this.onNoteListener = onNoteListener;
            itemView.setOnClickListener(this);
        }

        void bind(int listIndex){
            if (actorsPhotos.size() != 0){
                actorPhoto.setImageDrawable(actorsPhotos.get(characters.get(listIndex).name));
            }
            else{
                actorPhoto.setImageDrawable(loadingImage);
            }

            if (characters.get(listIndex).name.length() > 0) {
                characterName.setVisibility(View.VISIBLE);
                characterName.setText(characters.get(listIndex).name);
            }
            else {
                characterName.setVisibility(View.GONE);
            }

            if (characters.get(listIndex).species.length() > 0) {
                characterSpecies.setVisibility(View.VISIBLE);
                characterSpecies.setText("Species: " + characters.get(listIndex).species);
            }
            else {
                characterSpecies.setVisibility(View.GONE);
            }

            if (characters.get(listIndex).gender.length() > 0) {
                characterGender.setVisibility(View.VISIBLE);
                characterGender.setText("Gender: " + characters.get(listIndex).gender);
            }
            else {
                characterGender.setVisibility(View.GONE);
            }

            if (characters.get(listIndex).house.length() > 0) {
                characterHouse.setVisibility(View.VISIBLE);
                characterHouse.setText("House: " + characters.get(listIndex).house);
            }
            else {
                characterHouse.setVisibility(View.GONE);
            }

            if (characters.get(listIndex).dateOfBirth.length() > 0) {
                characterDateOfBirth.setVisibility(View.VISIBLE);
                characterDateOfBirth.setText("Date of birth: " + characters.get(listIndex).dateOfBirth);
            }
            else {
                characterDateOfBirth.setVisibility(View.GONE);
            }

            if (characters.get(listIndex).ancestry.length() > 0) {
                characterAncestry.setVisibility(View.VISIBLE);
                characterAncestry.setText("Ancestry: " + characters.get(listIndex).ancestry);
            }
            else {
                characterAncestry.setVisibility(View.GONE);
            }

            if (characters.get(listIndex).eyeColour.length() > 0) {
                characterEyeColour.setVisibility(View.VISIBLE);
                characterEyeColour.setText("Eye colour: " + characters.get(listIndex).eyeColour);
            }
            else {
                characterEyeColour.setVisibility(View.GONE);
            }

            if (characters.get(listIndex).hairColour.length() > 0) {
                characterHairColour.setVisibility(View.VISIBLE);
                characterHairColour.setText("Hair colour: " + characters.get(listIndex).hairColour);
            }
            else {
                characterHairColour.setVisibility(View.GONE);
            }

            if (characters.get(listIndex).patronus.length() > 0) {
                characterPatronus.setVisibility(View.VISIBLE);
                characterPatronus.setText("Patronus: " + characters.get(listIndex).patronus);
            }
            else {
                characterPatronus.setVisibility(View.GONE);
            }
        }

        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }

    public interface OnNoteListener{
        void onNoteClick(int position);
    }
}
