package com.example.carnumbersdatabase.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.carnumbersdatabase.model.Fines;

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

    @Query("UPDATE fines SET fineIsPayed = :payed WHERE id LIKE :id")
    void setFinePayStatus(int id, boolean payed);

}
