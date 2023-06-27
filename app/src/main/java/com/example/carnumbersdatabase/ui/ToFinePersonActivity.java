package com.example.carnumbersdatabase.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.carnumbersdatabase.R;
import com.example.carnumbersdatabase.model.Fines;
import com.example.carnumbersdatabase.ui.viewModel.FineViewModel;
import com.google.android.material.textfield.TextInputLayout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ToFinePersonActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_fine_person);

        TextInputLayout fineTextEditText = findViewById(R.id.fineTextEditText);
        TextInputLayout fineAmountEditText = findViewById(R.id.fineAmountEditText);

        Button toFineButton = findViewById(R.id.toFineButton);
        Button goBackButton = findViewById(R.id.goBackButton);

        FineViewModel fineVM = new ViewModelProvider(this).get(FineViewModel.class);

        Intent intent = getIntent();

        boolean personHasLicense = intent.getBooleanExtra("personHasLicense", true);
        String personCarNumbers = intent.getStringExtra("carNumbers");
        String personRegionCode = intent.getStringExtra("regionCode");
        int finesCount = intent.getIntExtra("finesCount", 0);

        goBackButton.setOnClickListener(v -> {

            Intent goPersonFines = new Intent(ToFinePersonActivity.this, PersonFinesActivity.class);
            goPersonFines.putExtra("carNumbers", personCarNumbers);
            goPersonFines.putExtra("regionCode", personRegionCode);
            goPersonFines.putExtra("personHasLicense", personHasLicense);
            goPersonFines.putExtra("finesCount", finesCount);
            startActivity(goPersonFines);

        });

        toFineButton.setOnClickListener(v -> {

            String fineText = fineTextEditText.getEditText().getText().toString().trim();
            double fineAmount = Double.parseDouble(fineAmountEditText.getEditText().getText().toString().trim());
            boolean fineTextChecker;
            boolean fineAmountChecker;

            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
            Date dateObj = new Date();
            String fineDate = dateFormat.format(dateObj);

            if (fineText.isEmpty()) {
                fineTextChecker = false;
                fineTextEditText.setError("Описание нарушения не может быть пустым");
            } else if (fineText.length() > 60) {
                fineTextChecker = false;
                fineTextEditText.setError("Слишком длинный текст нарушения");
            } else {
                fineTextChecker = true;
                fineTextEditText.setError(null);
            }

            if (fineAmount > 9999999999.0d || String.valueOf(fineAmount).length() > 10){
                fineAmountChecker = false;
                fineAmountEditText.setError("Макс. сумма: 9999999999");
            } else {
                fineAmountChecker = true;
                fineAmountEditText.setError(null);
            }

            if (fineTextChecker && fineAmountChecker){

                fineVM.insert(new Fines(null, personCarNumbers + personRegionCode, fineText, String.valueOf(fineAmount) + "₽", false, fineDate));
                Toast.makeText(this, "Штраф выписан!", Toast.LENGTH_SHORT).show();
                Intent goPersonFines = new Intent(ToFinePersonActivity.this, PersonFinesActivity.class);
                goPersonFines.putExtra("carNumbers", personCarNumbers);
                goPersonFines.putExtra("regionCode", personRegionCode);
                goPersonFines.putExtra("personHasLicense", personHasLicense);
                goPersonFines.putExtra("finesCount", finesCount + 1);
                startActivity(goPersonFines);

            }

        });

    }
}
