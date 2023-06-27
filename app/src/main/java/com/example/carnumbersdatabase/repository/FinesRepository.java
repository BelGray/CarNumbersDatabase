package com.example.carnumbersdatabase.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.carnumbersdatabase.database.FinesDatabase;
import com.example.carnumbersdatabase.database.NumbersDatabase;
import com.example.carnumbersdatabase.database.dao.FinesDao;
import com.example.carnumbersdatabase.model.Fines;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FinesRepository {

    private final FinesDao finesDao;

    private final ExecutorService service = Executors.newSingleThreadExecutor();

    public FinesRepository(Application application){
        FinesDatabase finesDatabase = FinesDatabase.getInstance(application);
        finesDao = finesDatabase.finesDao();
    }

    public void insert(Fines fines){
        service.execute(() -> finesDao.insert(fines));
    }

    public void deletePerson(String carNumbersWithRegionCode){
        service.execute(() -> finesDao.deletePerson(carNumbersWithRegionCode));
    }

    public LiveData<List<Fines>> getFines(String carNumbersWithRegionCode){
        return finesDao.getFines(carNumbersWithRegionCode);
    }

    public void update(Fines fines){
        service.execute(() -> finesDao.update(fines));
    }

    public void setFinePayStatus(int id, boolean payed){
        service.execute(() -> finesDao.setFinePayStatus(id, payed));
    }

}
