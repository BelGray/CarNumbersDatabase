package com.example.carnumbersdatabase.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.carnumbersdatabase.R;
import com.example.carnumbersdatabase.ui.viewModel.FineViewModel;
import com.example.carnumbersdatabase.ui.viewModel.NumberViewModel;
import com.google.android.material.textfield.TextInputLayout;

import java.util.concurrent.atomic.AtomicBoolean;

public class ShowPersonInfoActivity extends AppCompatActivity {

    private int finesCount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_person_info);

        Intent goMainActivity = new Intent(ShowPersonInfoActivity.this, MainActivity.class);
        Intent intent = getIntent();

        Button saveButton = findViewById(R.id.saveButton);
        Button finesButton = findViewById(R.id.personFinesButton);
        Button deleteButton = findViewById(R.id.personDeleteButton);


        TextInputLayout personNameEditText = findViewById(R.id.personNameEditText);
        TextInputLayout personLastnameEditText = findViewById(R.id.personLastnameEditText);
        TextInputLayout personBirthdateEditText = findViewById(R.id.personBirthdateEditText);
        TextInputLayout personAddressEditText = findViewById(R.id.personAddressEditText);
        TextInputLayout personTransportNameEditText = findViewById(R.id.personTransportNameEditText);
        TextInputLayout personPhoneNumberEditText = findViewById(R.id.personPhoneNumberEditText);

        TextView personStatusTextView = findViewById(R.id.personStatusTextView);

        String carNumbers = intent.getStringExtra("carNumbers");
        String regionCode = intent.getStringExtra("regionCode");
        boolean personHasLicense = intent.getBooleanExtra("personHasLicense", true);

        FineViewModel fineVM = new ViewModelProvider(this).get(FineViewModel.class);
        NumberViewModel numberVM = new ViewModelProvider(this).get(NumberViewModel.class);

        fineVM.getFines(carNumbers + regionCode).observe(this, fines -> {

            finesCount = fines.size();

            if (!personHasLicense) {
                personStatusTextView.setTextColor(Color.parseColor("#AE1D1D"));
                personStatusTextView.setText(R.string.person_without_license_warning);
            }

            else if (finesCount > 0) {
                personStatusTextView.setTextColor(Color.parseColor("#C29E18"));
                personStatusTextView.setText(R.string.person_with_violation);
            }

            else if (finesCount == 0) {
                personStatusTextView.setTextColor(Color.parseColor("#325C22"));
                personStatusTextView.setText(R.string.person_without_violation);
            }

        });


        personNameEditText.getEditText().setText(intent.getStringExtra("personName"));
        personLastnameEditText.getEditText().setText(intent.getStringExtra("personLastname"));
        personBirthdateEditText.getEditText().setText(intent.getStringExtra("personBirthdate"));
        personAddressEditText.getEditText().setText(intent.getStringExtra("personAddress"));
        personTransportNameEditText.getEditText().setText(intent.getStringExtra("personTransportName"));
        personPhoneNumberEditText.getEditText().setText(intent.getStringExtra("personPhoneNumber"));


        saveButton.setOnClickListener(v -> {

            String personName = personNameEditText.getEditText().getText().toString().trim();
            String personLastname = personLastnameEditText.getEditText().getText().toString().trim();
            String personBirthdate = personBirthdateEditText.getEditText().getText().toString().trim();
            String personAddress = personAddressEditText.getEditText().getText().toString().trim();
            String personTransport = personTransportNameEditText.getEditText().getText().toString().trim();
            String personPhone = personPhoneNumberEditText.getEditText().getText().toString().trim();

            boolean nameChecker;
            boolean lastnameChecker;
            boolean addressChecker;
            boolean transportChecker;
            boolean phoneChecker;

            if (personName.isEmpty()){
                nameChecker = false;
                personNameEditText.setError("Заполните имя");
            } else {
                nameChecker = true;
                personNameEditText.setError(null);
            }


            if (personLastname.isEmpty()){
                lastnameChecker = false;
                personLastnameEditText.setError("Заполните фамилию");
            } else {
                lastnameChecker = true;
                personLastnameEditText.setError(null);
            }


            if (personAddress.isEmpty()){
                addressChecker = false;
                personAddressEditText.setError("Заполните адрес проживания");
            } else {
                addressChecker = true;
                personAddressEditText.setError(null);
            }


            if (personTransport.isEmpty()){
                transportChecker = false;
                personTransportNameEditText.setError("Заполните название транспорта");
            } else {
                transportChecker = true;
                personTransportNameEditText.setError(null);
            }


            if (personPhone.length() < 10 || personPhone.length() != 12 && personPhone.startsWith("+7") || personPhone.length() != 11 && personPhone.startsWith("8")){
                phoneChecker = false;
                personPhoneNumberEditText.setError("Заполните номер телефона корректно");
            } else {
                phoneChecker = true;
                personPhoneNumberEditText.setError(null);
            }

            if (nameChecker && lastnameChecker && addressChecker && transportChecker && phoneChecker){
                numberVM.updatePersonByCarNumbers(personName, personLastname, personBirthdate, personAddress, personTransport, carNumbers.toUpperCase(), Integer.parseInt(regionCode), personPhone, personHasLicense);
                startActivity(goMainActivity);
                Toast.makeText(ShowPersonInfoActivity.this, "Данные сохранены успешно!", Toast.LENGTH_SHORT).show();
            }


        });

        finesButton.setOnClickListener(v -> {

            Intent goPersonFinesActivity = new Intent(ShowPersonInfoActivity.this, PersonFinesActivity.class);
            goPersonFinesActivity.putExtra("carNumbers", carNumbers);
            goPersonFinesActivity.putExtra("regionCode", regionCode);
            goPersonFinesActivity.putExtra("finesCount", finesCount);
            goPersonFinesActivity.putExtra("personHasLicense", personHasLicense);
            startActivity(goPersonFinesActivity);

        });

        deleteButton.setOnClickListener(v -> {
            numberVM.deletePerson(carNumbers, Integer.parseInt(regionCode));
            startActivity(goMainActivity);
            Toast.makeText(ShowPersonInfoActivity.this, "Данные о номерах удалены!", Toast.LENGTH_SHORT).show();
        });



    }
}
