package com.example.sportresults;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

public class Favourites extends MainActivity {

    TextView favouritesTitle;
    TextView favouritesText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        favouritesTitle = findViewById(R.id.favouritesTitle);
        favouritesText = findViewById(R.id.favouritesText);

        Intent i = getIntent();
        String message = i.getStringExtra("message");
        favouritesText.setText(message);

    }

}