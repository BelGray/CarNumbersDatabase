package com.example.carnumbersdatabase.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.carnumbersdatabase.database.dao.NumbersDao;
import com.example.carnumbersdatabase.database.NumbersDatabase;
import com.example.carnumbersdatabase.model.Numbers;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NumberRepository {

    private final NumbersDao numbersDao;
    private final ExecutorService service = Executors.newSingleThreadExecutor();

    public NumberRepository(Application application) {
        NumbersDatabase numbersDatabase = NumbersDatabase.getInstance(application);
        numbersDao = numbersDatabase.numbersDao();
    }

    public void deletePerson(String personCarNumbers, int personRegionCode) {
        service.execute(() -> numbersDao.deletePerson(personCarNumbers, personRegionCode));
    }

    public void insert(Numbers numbers) {
        service.execute(() -> numbersDao.insert(numbers));
    }

    public void update(Numbers numbers) {
        service.execute(() -> numbersDao.update(numbers));
    }

    public LiveData<List<Numbers>> getPersonByCarNumbers(String personCarNumbers, int personRegionCode) {
        return numbersDao.getPersonByCarNumbers(personCarNumbers, personRegionCode);
    }

    public void setPersonWithLicense(String personCarNumbers, int personRegionCode, boolean personHasLicense){
        service.execute(() -> numbersDao.setPersonWithLicense(personCarNumbers, personRegionCode, personHasLicense));
    }

    public void updatePersonByCarNumbers(String personName, String personLastname, String personBirthdate, String personAddress, String personTransport, String carNumbers, int regionCode, String personPhone, boolean personHasLicense){
        service.execute(() -> numbersDao.updatePersonByCarNumbers(personName, personLastname, personBirthdate, personAddress, personTransport, carNumbers, regionCode, personPhone, personHasLicense));
    }



}
