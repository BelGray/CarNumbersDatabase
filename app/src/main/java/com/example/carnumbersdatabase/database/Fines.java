package com.example.carnumbersdatabase.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "fines")
public class Fines implements Serializable {

    @PrimaryKey(autoGenerate = true)
    int id = 0;

    @ColumnInfo(name = "carNumbersWithRegionCode")
    String carNumbersWithRegionCode;

    @ColumnInfo(name = "fineText")
    String fineText;

    @ColumnInfo(name = "fineAmount")
    String fineAmount;

    @ColumnInfo(name = "fineIsPayed")
    boolean fineIsPayed;


}
