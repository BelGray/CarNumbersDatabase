package com.example.carnumbersdatabase.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FinesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Fines fines);

    @Query("DELETE FROM fines WHERE carNumbersWithRegionCode LIKE :carNumbersWithRegionCode")
    void deletePerson(String carNumbersWithRegionCode);

    @Query("SELECT * FROM fines WHERE carNumbersWithRegionCode LIKE :carNumbersWithRegionCode")
    LiveData<List<Fines>> getFines(String carNumbersWithRegionCode);

    @Update(onConflict = OnConflictStrategy.ROLLBACK)
    void update(Fines fines);

}
