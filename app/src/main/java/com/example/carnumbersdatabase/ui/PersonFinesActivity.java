package com.example.carnumbersdatabase.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carnumbersdatabase.R;
import com.example.carnumbersdatabase.ui.adapter.FineAdapter;
import com.example.carnumbersdatabase.ui.viewModel.FineViewModel;
import com.example.carnumbersdatabase.ui.viewModel.NumberViewModel;

public class PersonFinesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_fines);

        Intent intent = getIntent();

        ImageView noFinesImg = findViewById(R.id.noFinesImageView);

        Button giveLicenseButton = findViewById(R.id.personGiveLicenseButton);
        Button removeLicenseButton = findViewById(R.id.personRemoveLicenseButton);
        Button toFineButton = findViewById(R.id.personToFineButton);
        Button goBackButton = findViewById(R.id.finesGoBackButton);
        boolean personHasLicense = intent.getBooleanExtra("personHasLicense", true);
        String personCarNumbers = intent.getStringExtra("carNumbers");
        String personRegionCode = intent.getStringExtra("regionCode");
        int finesCount = intent.getIntExtra("finesCount", 0);

        NumberViewModel numberVM = new ViewModelProvider(this).get(NumberViewModel.class);
        FineViewModel fineVM = new ViewModelProvider(this).get(FineViewModel.class);

        RecyclerView finesRecyclerView = findViewById(R.id.finesRecyclerView);
        FineAdapter adapter = new FineAdapter();

        finesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        finesRecyclerView.setHasFixedSize(true);
        finesRecyclerView.setAdapter(adapter);

        if (finesCount <= 0){
            finesRecyclerView.setVisibility(View.GONE);
            noFinesImg.setVisibility(View.VISIBLE);
        } else {
            noFinesImg.setVisibility(View.GONE);
            finesRecyclerView.setVisibility(View.VISIBLE);
        }

        if (personHasLicense){
            giveLicenseButton.setVisibility(View.GONE);
            removeLicenseButton.setVisibility(View.VISIBLE);
        } else {
            giveLicenseButton.setVisibility(View.VISIBLE);
            removeLicenseButton.setVisibility(View.GONE);
        }

        fineVM.getFines(personCarNumbers + personRegionCode).observe(this, adapter::setFinesList);

        adapter.setOnConfirmFineIsPayedButtonClickListener(fines -> {

            fineVM.setFinePayStatus(fines.getId(), true);
            Toast.makeText(this, "Оплата штрафа подтверждена!", Toast.LENGTH_SHORT).show();

        });

        removeLicenseButton.setOnClickListener(v -> {

            numberVM.setPersonWithLicense(personCarNumbers, Integer.parseInt(personRegionCode), false);
            Toast.makeText(PersonFinesActivity.this, "Гражданин лишен водительского удостоверения!", Toast.LENGTH_SHORT).show();
            removeLicenseButton.setVisibility(View.GONE);
            giveLicenseButton.setVisibility(View.VISIBLE);

        });

        giveLicenseButton.setOnClickListener(v -> {

            numberVM.setPersonWithLicense(personCarNumbers, Integer.parseInt(personRegionCode), true);
            Toast.makeText(PersonFinesActivity.this, "Гражданину выдано водительское удостоверение!", Toast.LENGTH_SHORT).show();
            giveLicenseButton.setVisibility(View.GONE);
            removeLicenseButton.setVisibility(View.VISIBLE);


        });

        goBackButton.setOnClickListener(v -> {

            numberVM.getPersonByCarNumbers(personCarNumbers, Integer.parseInt(personRegionCode)).observe(this, numbers -> {

                Intent goNumbersInfo = new Intent(PersonFinesActivity.this, ShowPersonInfoActivity.class);
                goNumbersInfo.putExtra("carNumbers", personCarNumbers);
                goNumbersInfo.putExtra("regionCode", personRegionCode);
                goNumbersInfo.putExtra("personName", numbers.get(0).getPersonName());
                goNumbersInfo.putExtra("personLastname", numbers.get(0).getPersonLastname());
                goNumbersInfo.putExtra("personBirthdate", numbers.get(0).getPersonBirthdate());
                goNumbersInfo.putExtra("personAddress", numbers.get(0).getPersonAddress());
                goNumbersInfo.putExtra("personTransportName", numbers.get(0).getPersonTransportName());
                goNumbersInfo.putExtra("personPhoneNumber", numbers.get(0).getPersonPhoneNumber());
                goNumbersInfo.putExtra("personHasLicense", numbers.get(0).isPersonHasLicense());
                startActivity(goNumbersInfo);

            });

        });

        toFineButton.setOnClickListener(v -> {

            Intent goToFine = new Intent(PersonFinesActivity.this, ToFinePersonActivity.class);
            goToFine.putExtra("carNumbers", personCarNumbers);
            goToFine.putExtra("regionCode", personRegionCode);
            goToFine.putExtra("personHasLicense", personHasLicense);
            goToFine.putExtra("finesCount", finesCount);
            startActivity(goToFine);

        });

    }

}
