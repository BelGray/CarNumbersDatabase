package com.example.carnumbersdatabase.database;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NumbersDao {

    @Query("DELETE FROM numbers WHERE personCarNumbers LIKE :personCarNumbers AND personRegionCode LIKE :personRegionCode")
    void deletePerson(String personCarNumbers, int personRegionCode);

    @Query("SELECT * FROM numbers WHERE personCarNumbers LIKE :personCarNumbers AND personRegionCode LIKE :personRegionCode")
    LiveData<List<Numbers>> getPersonByCarNumbers(String personCarNumbers, int personRegionCode);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Numbers numbers);

}
