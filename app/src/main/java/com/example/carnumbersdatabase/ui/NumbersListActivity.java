package com.example.carnumbersdatabase.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carnumbersdatabase.R;
import com.example.carnumbersdatabase.model.Numbers;
import com.example.carnumbersdatabase.ui.adapter.FineAdapter;
import com.example.carnumbersdatabase.ui.adapter.NumberAdapter;
import com.example.carnumbersdatabase.ui.viewModel.FineViewModel;
import com.example.carnumbersdatabase.ui.viewModel.NumberViewModel;

import java.util.List;

public class NumbersListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers_list);

        Button goBackButton = findViewById(R.id.goBackButton);

        ImageView emptyDatabaseImageView = findViewById(R.id.databaseIsEmptyImageView);
        RecyclerView numbersListRecyclerView = findViewById(R.id.numbersListRecyclerView);
        NumberAdapter adapter = new NumberAdapter();

        numbersListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        numbersListRecyclerView.setHasFixedSize(true);
        numbersListRecyclerView.setAdapter(adapter);

        FineViewModel fineVM = new ViewModelProvider(this).get(FineViewModel.class);
        NumberViewModel numberVM = new ViewModelProvider(this).get(NumberViewModel.class);

        numberVM.getAllNumbers().observe(this, numbers -> {

            if (numbers.isEmpty()){
                emptyDatabaseImageView.setVisibility(View.VISIBLE);
                numbersListRecyclerView.setVisibility(View.GONE);
            }
            else {
                adapter.setNumbersList(numbers);
                emptyDatabaseImageView.setVisibility(View.GONE);
                numbersListRecyclerView.setVisibility(View.VISIBLE);
            }

        });


        adapter.setOnDeleteButtonClickListener(numbers -> {
            numberVM.deletePerson(numbers.getPersonCarNumbers(), numbers.getPersonRegionCode());
            fineVM.deletePerson(numbers.getPersonCarNumbers() + String.valueOf(numbers.getPersonRegionCode()));
            Toast.makeText(this, "Данные о номерах удалены!", Toast.LENGTH_SHORT).show();
        });

        adapter.setOnItemClickListener(numbers -> {

            Intent goNumbersInfo = new Intent(NumbersListActivity.this, ShowPersonInfoActivity.class);
            goNumbersInfo.putExtra("carNumbers", numbers.getPersonCarNumbers());
            goNumbersInfo.putExtra("regionCode", String.valueOf(numbers.getPersonRegionCode()));
            goNumbersInfo.putExtra("personName", numbers.getPersonName());
            goNumbersInfo.putExtra("personLastname", numbers.getPersonLastname());
            goNumbersInfo.putExtra("personBirthdate", numbers.getPersonBirthdate());
            goNumbersInfo.putExtra("personAddress", numbers.getPersonAddress());
            goNumbersInfo.putExtra("personTransportName", numbers.getPersonTransportName());
            goNumbersInfo.putExtra("personPhoneNumber", numbers.getPersonPhoneNumber());
            goNumbersInfo.putExtra("personHasLicense", numbers.isPersonHasLicense());
            startActivity(goNumbersInfo);
        });

        goBackButton.setOnClickListener(v -> {

            startActivity(new Intent(NumbersListActivity.this, MainActivity.class));

        });

    }


}
