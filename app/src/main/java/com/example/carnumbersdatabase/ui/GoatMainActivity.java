package com.example.carnumbersdatabase.ui;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carnumbersdatabase.R;

public class GoatMainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goat_main_activity);

        final MediaPlayer goatScream = MediaPlayer.create(this, R.raw.scary_scream);
        final MediaPlayer goatSound = MediaPlayer.create(this, R.raw.goat_sound);

        goatSound.start();

        Button deadSearchButton = findViewById(R.id.searchButton);
        Button deadAddButton = findViewById(R.id.addNumbersButton);
        ImageView goat = findViewById(R.id.goatImage);

        deadSearchButton.setOnClickListener(v -> {

            goatScream.start();
            goat.setVisibility(View.VISIBLE);
            this.finishAffinity();

        });

        deadAddButton.setOnClickListener(v -> {

            goatScream.start();
            goat.setVisibility(View.VISIBLE);
            this.finishAffinity();

        });

    }
}
