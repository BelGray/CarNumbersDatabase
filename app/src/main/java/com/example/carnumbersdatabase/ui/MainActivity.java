package com.example.carnumbersdatabase.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.carnumbersdatabase.R;
import com.example.carnumbersdatabase.ui.viewModel.NumberViewModel;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NumberViewModel numberViewModel = new ViewModelProvider(this).get(NumberViewModel.class);

        Pattern cyrillic = Pattern.compile("^[а-яА-Я]+$");
        Pattern latin = Pattern.compile("^[a-zA-Z]+$");
        String rangeRegex = "[0-9]+";

        Button searchButton = findViewById(R.id.searchButton);
        Button addNumbersButton = findViewById(R.id.addNumbersButton);
        TextInputLayout searchCarNumbersEditText = findViewById(R.id.searchCarNumbersEditText);
        TextInputLayout searchRegionCodeEditText = findViewById(R.id.searchRegionCodeEditText);
        TextView searchNumbersTextView = findViewById(R.id.searchNumbersTextView);

        searchButton.setOnClickListener(v -> {
            String regionCode = searchRegionCodeEditText.getEditText().getText().toString().trim();
            String carNumbers = searchCarNumbersEditText.getEditText().getText().toString().trim();

            Matcher carNumbersCyrillic = cyrillic.matcher(carNumbers);
            Matcher carNumbersLatin = latin.matcher(carNumbers);

            boolean searchCarNumbersFieldStatusOk;
            boolean searchRegionCodeFieldStatusOk;

            if (carNumbers.equalsIgnoreCase("666") && regionCode.equalsIgnoreCase("666")){

                startActivity(new Intent(MainActivity.this, GoatMainActivity.class));

            }

            if (regionCode.isEmpty() || regionCode.length() > 3) {
                searchRegionCodeFieldStatusOk = false;
                searchRegionCodeEditText.setError("Некорректный код региона!");
            } else {
                searchRegionCodeFieldStatusOk = true;
                searchRegionCodeEditText.setError(null);
            }


            if (carNumbers.length() != 6 || !carNumbersCyrillic.find() && carNumbersLatin.find() || carNumbersLatin.find() || !(carNumbers.substring(1, 4).matches(rangeRegex)) || carNumbers.matches(rangeRegex)) {
                searchCarNumbersFieldStatusOk = false;
                searchCarNumbersEditText.setError("Некорректные номера автомобиля. Пример: C777PУ");
            } else {
                searchCarNumbersFieldStatusOk = true;
                searchCarNumbersEditText.setError(null);
            }

            if (searchCarNumbersFieldStatusOk && searchRegionCodeFieldStatusOk) {
                int region = Integer.parseInt(regionCode);
                numberViewModel.getPersonByCarNumbers(carNumbers, region).observe(this, numbers -> {

                    if (numbers.isEmpty()) {
                        Toast.makeText(MainActivity.this, "Номеров в базе не обнаружено!", Toast.LENGTH_SHORT).show();
                        return;
                    }


                    Intent goNumbersInfo = new Intent(MainActivity.this, ShowPersonInfoActivity.class);
                    goNumbersInfo.putExtra("carNumbers", carNumbers);
                    goNumbersInfo.putExtra("regionCode", regionCode);
                    goNumbersInfo.putExtra("personName", numbers.get(0).getPersonName());
                    goNumbersInfo.putExtra("personLastname", numbers.get(0).getPersonLastname());
                    goNumbersInfo.putExtra("personBirthdate", numbers.get(0).getPersonBirthdate());
                    goNumbersInfo.putExtra("personAddress", numbers.get(0).getPersonAddress());
                    goNumbersInfo.putExtra("personTransportName", numbers.get(0).getPersonTransportName());
                    goNumbersInfo.putExtra("personPhoneNumber", numbers.get(0).getPersonPhoneNumber());
                    goNumbersInfo.putExtra("personHasLicense", numbers.get(0).isPersonHasLicense());
                    startActivity(goNumbersInfo);

                });
            }
        });

        addNumbersButton.setOnClickListener(v -> {

            String regionCode = searchRegionCodeEditText.getEditText().getText().toString().trim();
            String carNumbers = searchCarNumbersEditText.getEditText().getText().toString().trim();

            Matcher carNumbersCyrillic = cyrillic.matcher(carNumbers);
            Matcher carNumbersLatin = latin.matcher(carNumbers);

            boolean searchCarNumbersFieldStatusOk;
            boolean searchRegionCodeFieldStatusOk;

            if (regionCode.isEmpty() || regionCode.length() > 3) {
                searchRegionCodeFieldStatusOk = false;
                searchRegionCodeEditText.setError("Некорректный код региона!");
            } else {
                searchRegionCodeFieldStatusOk = true;
                searchRegionCodeEditText.setError(null);
            }


            if (carNumbers.length() != 6 || !carNumbersCyrillic.find() && carNumbersLatin.find() || carNumbersLatin.find() || !(carNumbers.substring(1, 4).matches(rangeRegex)) || carNumbers.matches(rangeRegex)) {
                searchCarNumbersFieldStatusOk = false;
                searchCarNumbersEditText.setError("Некорректные номера автомобиля. Пример: C777PУ");
            } else {
                searchCarNumbersFieldStatusOk = true;
                searchCarNumbersEditText.setError(null);
            }

            if (searchCarNumbersFieldStatusOk && searchRegionCodeFieldStatusOk) {
                int region = Integer.parseInt(regionCode);
                numberViewModel.getPersonByCarNumbers(carNumbers.toUpperCase(), region).observe(this, numbers -> {

                    if (numbers.isEmpty()) {
                        Intent goNumbersInfo = new Intent(MainActivity.this, AddPersonInfoActivity.class);
                        goNumbersInfo.putExtra("carNumbers", carNumbers);
                        goNumbersInfo.putExtra("regionCode", regionCode);
                        startActivity(goNumbersInfo);
                        return;
                    }

                    Toast.makeText(MainActivity.this, "Номера уже существуют в базе!", Toast.LENGTH_SHORT).show();

                });
            }

        });

    }
}