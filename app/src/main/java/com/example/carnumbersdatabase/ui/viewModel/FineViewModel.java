package com.example.carnumbersdatabase.ui.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.carnumbersdatabase.model.Fines;
import com.example.carnumbersdatabase.repository.FinesRepository;
import com.example.carnumbersdatabase.repository.NumberRepository;

import java.util.List;

public class FineViewModel extends AndroidViewModel {

    private final FinesRepository finesRepository;

    public FineViewModel(@NonNull Application application) {
        super(application);
        finesRepository = new FinesRepository(application);
    }

    public void insert(Fines fines){
        finesRepository.insert(fines);
    }

    public void deletePerson(String carNumbersWithRegionCode){
        finesRepository.deletePerson(carNumbersWithRegionCode);
    }

    public LiveData<List<Fines>> getFines(String carNumbersWithRegionCode){
        return finesRepository.getFines(carNumbersWithRegionCode);
    }

    public void update(Fines fines){
        finesRepository.update(fines);
    }

}
