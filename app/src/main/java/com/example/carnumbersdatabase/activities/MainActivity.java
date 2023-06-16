package com.example.carnumbersdatabase.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.carnumbersdatabase.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button searchButton = findViewById(R.id.searchButton);
        Button addNumbersButton = findViewById(R.id.addNumbersButton);

        // Обработка нажатий на любой UI происходит в onCreate.
        // Каждой кнопке свой отдельный интерфейс View.OnClickListener
        searchButton.setOnClickListener(new View.OnClickListener() { // Объекту searchButton мы вызываем метод setOnClickListener, в который мы передаем реализацию интерфейса. Это называет механизм обратного вызова
            @Override
            public void onClick(View v) { // v - параметр! (2)
                // Вот здесь происходит действие при нажатии на кнопку
                // Каждой отдельной кнопке совоя собственная обработка нажатия. У
                // тебя в прграмме не будет же милиоон кнопок на которые наживыешь и происходит
                // одно и то же действие


                // У тебя тут будет переход на другую активити ShowNumbersInfoActivity
                // вот так происходит нормальный переход на другую активити.
                Intent goNumbersInfo = new Intent(MainActivity.this, ShowNumbersInfoActivity.class);
                startActivity(goNumbersInfo);
            }
        });

        // внутри каждой активити свой собственный набор UI элементов, значит внутри каждой активити нужно реализовывать нажатие на кнопку.
        // Обработаем нажатие на другую кнопку addNumbersButton.

        // (2)
        // Точно так же - вызываем метод addNumbersButton, только в параметр не будем пихать интрейфейс - это тоже
        // уже устаревший метод. В параметр мы передаем лямбда функцию. В методе onClick(View v) один параметр - v
        // занчит просто пишем (v ->) то что будет после стрелочки - реализация метода onClick.
        addNumbersButton.setOnClickListener(v -> {
            // Все работает абсолютно идентично searchButton. Мы делаем реализацию того же метода,
            // только на современный лад.

            Toast.makeText(MainActivity.this, "ПРОИЗОШЛО НАЖАТИЕ НА КНОПКУ!", Toast.LENGTH_SHORT).show();

        });

    }
}