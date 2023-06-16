package com.example.carnumbersdatabase.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "numbers")
public class Numbers implements Serializable {

    @PrimaryKey(autoGenerate = true)
    int id = 0;

    @ColumnInfo(name = "personName")
    String personName;

    @ColumnInfo(name = "personLastname")
    String personLastname;

    @ColumnInfo(name = "personBirthdate")
    String personBirthdate;

    @ColumnInfo(name="personAddress")
    String personAddress;

    @ColumnInfo(name="personTransportName")
    String personTransportName;

    @ColumnInfo(name="personCarNumbers")
    String personCarNumbers;

    @ColumnInfo(name="personRegionCode")
    int personRegionCode;

}
