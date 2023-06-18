package com.example.carnumbersdatabase.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.example.carnumbersdatabase.R;
import com.example.carnumbersdatabase.database.Numbers;
import com.example.carnumbersdatabase.database.NumbersDatabase;
import com.google.android.material.textfield.TextInputLayout;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NumbersDatabase numbersDatabase = NumbersDatabase.getInstance(this);
        Pattern cyrillic = Pattern.compile("^[а-яА-Я]+$");
        Pattern latin = Pattern.compile("^[a-zA-Z]+$");
        String rangeRegex = "[0-9]+";
        Button searchButton = findViewById(R.id.searchButton);
        Button addNumbersButton = findViewById(R.id.addNumbersButton);
        TextInputLayout searchCarNumbersEditText = findViewById(R.id.searchCarNumbersEditText);
        TextInputLayout searchRegionCodeEditText = findViewById(R.id.searchRegionCodeEditText);
        TextView searchNumbersTextView = findViewById(R.id.searchNumbersTextView);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String regionCode = searchRegionCodeEditText.getEditText().getText().toString().trim();
                String carNumbers = searchCarNumbersEditText.getEditText().getText().toString().trim();
                Matcher carNumbersCyrillic = cyrillic.matcher(carNumbers);
                Matcher carNumbersLatin = latin.matcher(carNumbers);
                boolean searchCarNumbersFieldStatusOk;
                boolean searchRegionCodeFieldStatusOk;

                if (regionCode.isEmpty() || regionCode.length() > 3){
                    searchRegionCodeFieldStatusOk = false;
                    searchRegionCodeEditText.setError("Некорректный код региона!");
                } else {
                    searchRegionCodeFieldStatusOk = true;
                    searchRegionCodeEditText.setError(null);
                }


                if (carNumbers.length() != 6 || !carNumbersCyrillic.find() && carNumbersLatin.find() || carNumbersLatin.find() || !(carNumbers.substring(1, 4).matches(rangeRegex)) || carNumbers.matches(rangeRegex)){
                    searchCarNumbersFieldStatusOk = false;
                    searchCarNumbersEditText.setError("Некорректные номера автомобиля. Пример: C777PУ");
                }
                else {
                    searchCarNumbersFieldStatusOk = true;
                    searchCarNumbersEditText.setError(null);
                }

                if (searchCarNumbersFieldStatusOk && searchRegionCodeFieldStatusOk) {
                    searchNumbersTextView.setText("Поиск по базе");
                    searchNumbersTextView.setTextColor(Color.parseColor("#142899"));
                    try {
                        LiveData<List<Numbers>> numbersList = numbersDatabase.numbersDao().getPersonByCarNumbers(carNumbers, Integer.parseInt(regionCode));
                    } catch (Exception exception){
                        searchNumbersTextView.setText("Не найдено");
                        searchNumbersTextView.setTextColor(Color.parseColor("#AE1D1D"));
                    }
                }


                //Intent goNumbersInfo = new Intent(MainActivity.this, ShowNumbersInfoActivity.class);
                //startActivity(goNumbersInfo);
            }
        });

        addNumbersButton.setOnClickListener(v -> {

            Toast.makeText(MainActivity.this, "ПРОИЗОШЛО НАЖАТИЕ НА КНОПКУ!", Toast.LENGTH_SHORT).show();

        });

    }
}