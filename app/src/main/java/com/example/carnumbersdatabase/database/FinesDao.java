package com.example.carnumbersdatabase.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FinesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Fines fines);

    @Query("DELETE FROM fines WHERE carNumbersWithRegionCode LIKE :carNumbersWithRegionCode")
    void deletePerson(String carNumbersWithRegionCode);

    @Query("SELECT * FROM fines WHERE carNumbersWithRegionCode LIKE :carNumbersWithRegionCode")
    List<Fines> getFines(String carNumbersWithRegionCode);

}
