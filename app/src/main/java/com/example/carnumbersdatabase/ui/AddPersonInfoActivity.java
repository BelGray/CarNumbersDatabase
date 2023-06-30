package com.example.carnumbersdatabase.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.carnumbersdatabase.R;
import com.example.carnumbersdatabase.model.Numbers;
import com.example.carnumbersdatabase.ui.viewModel.FineViewModel;
import com.example.carnumbersdatabase.ui.viewModel.NumberViewModel;
import com.google.android.material.textfield.TextInputLayout;

public class AddPersonInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person_info);

        Intent goMainActivity = new Intent(AddPersonInfoActivity.this, MainActivity.class);

        Button saveButton = findViewById(R.id.saveButton);
        Button goBackButton = findViewById(R.id.goBackButton);

        TextInputLayout personNameEditText = findViewById(R.id.personNameEditText);
        TextInputLayout personLastnameEditText = findViewById(R.id.personLastnameEditText);
        TextInputLayout personBirthdateEditText = findViewById(R.id.personBirthdateEditText);
        TextInputLayout personAddressEditText = findViewById(R.id.personAddressEditText);
        TextInputLayout personTransportNameEditText = findViewById(R.id.personTransportNameEditText);
        TextInputLayout personPhoneNumberEditText = findViewById(R.id.personPhoneNumberEditText);

        NumberViewModel numberVM = new ViewModelProvider(this).get(NumberViewModel.class);

        saveButton.setOnClickListener(v -> {

            Intent intent = getIntent();

            String carNumbers = intent.getStringExtra("carNumbers");
            String regionCode = intent.getStringExtra("regionCode");

            String personName = personNameEditText.getEditText().getText().toString().replaceAll("\\s", "");
            String personLastname = personLastnameEditText.getEditText().getText().toString().replaceAll("\\s", "");
            String personBirthdate = personBirthdateEditText.getEditText().getText().toString().replaceAll("\\s", "");
            String personAddress = personAddressEditText.getEditText().getText().toString().trim();
            String personTransport = personTransportNameEditText.getEditText().getText().toString().trim();
            String personPhone = personPhoneNumberEditText.getEditText().getText().toString().replaceAll("\\s", "");

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


            if (personPhone.length() != 12 && personPhone.startsWith("+7") || personPhone.length() != 11 && personPhone.startsWith("8")){
                phoneChecker = false;
                personPhoneNumberEditText.setError("Заполните номер телефона корректно");
            } else {
                phoneChecker = true;
                personPhoneNumberEditText.setError(null);
            }

            if (nameChecker && lastnameChecker && addressChecker && transportChecker && phoneChecker){
                numberVM.insert(new Numbers(personName, personLastname, personBirthdate, personAddress, personTransport, carNumbers.toUpperCase(), Integer.parseInt(regionCode), personPhone, true));
                startActivity(goMainActivity);
                Toast.makeText(AddPersonInfoActivity.this, "Данные сохранены успешно!", Toast.LENGTH_SHORT).show();
            }


        });

        goBackButton.setOnClickListener(v -> {

            startActivity(goMainActivity);

        });

    }

}
