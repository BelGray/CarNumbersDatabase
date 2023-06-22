package com.example.carnumbersdatabase.ui.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.carnumbersdatabase.model.Numbers;
import com.example.carnumbersdatabase.repository.NumberRepository;

import java.util.List;

public class NumberViewModel extends AndroidViewModel {

    private final NumberRepository numberRepository;

    public NumberViewModel(@NonNull Application application) {
        super(application);
        numberRepository = new NumberRepository(application);
    }


    public void deletePerson(String personCarNumbers, int personRegionCode) {
        numberRepository.deletePerson(personCarNumbers, personRegionCode);
    }

    public void insert(Numbers numbers) {
        numberRepository.insert(numbers);
    }

    public void update(Numbers numbers) {
        numberRepository.update(numbers);
    }

    public LiveData<List<Numbers>> getPersonByCarNumbers(String personCarNumbers, int personRegionCode) {
        return numberRepository.getPersonByCarNumbers(personCarNumbers, personRegionCode);
    }


}
