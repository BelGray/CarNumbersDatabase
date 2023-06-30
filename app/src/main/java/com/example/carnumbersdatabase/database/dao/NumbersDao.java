package com.example.carnumbersdatabase.database.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.carnumbersdatabase.model.Numbers;

import java.util.List;

@Dao
public interface NumbersDao {

    @Query("UPDATE numbers SET personName = :personName, personLastname = :personLastname, personBirthdate = :personBirthdate, personAddress = :personAddress, personTransportName = :personTransport, personPhoneNumber = :personPhone, personHasLicense = :personHasLicense WHERE personCarNumbers LIKE :carNumbers AND personRegionCode LIKE :regionCode")
    void updatePersonByCarNumbers(String personName, String personLastname, String personBirthdate, String personAddress, String personTransport, String carNumbers, int regionCode, String personPhone, boolean personHasLicense);

    @Query("SELECT * FROM numbers WHERE personCarNumbers LIKE :personCarNumbers AND personRegionCode LIKE :personRegionCode")
    LiveData<List<Numbers>> getPersonByCarNumbers(String personCarNumbers, int personRegionCode);

    @Query("SELECT * FROM numbers")
    LiveData<List<Numbers>> getAllNumbers();

    @Query("DELETE FROM numbers WHERE personCarNumbers LIKE :personCarNumbers AND personRegionCode LIKE :personRegionCode")
    void deletePerson(String personCarNumbers, int personRegionCode);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Numbers numbers);

    @Query("UPDATE numbers SET personHasLicense = :personHasLicense WHERE personCarNumbers LIKE :personCarNumbers AND personRegionCode LIKE :personRegionCode")
    void setPersonWithLicense(String personCarNumbers, int personRegionCode, boolean personHasLicense);

    @Update(onConflict = OnConflictStrategy.ROLLBACK)
    void update(Numbers numbers);

}
