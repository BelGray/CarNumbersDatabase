package com.example.carnumbersdatabase.ui;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carnumbersdatabase.R;

public class PersonFinesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_fines);

        RecyclerView finesRecyclerView = findViewById(R.id.finesRecyclerView);




    }

}
