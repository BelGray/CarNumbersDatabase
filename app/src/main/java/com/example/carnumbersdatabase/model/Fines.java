package com.example.carnumbersdatabase.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "fines")
public class Fines implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id = 0;

    @ColumnInfo(name = "fineUuid")
    private String fineUuid;

    @ColumnInfo(name = "carNumbersWithRegionCode")
    private String carNumbersWithRegionCode;

    @ColumnInfo(name = "fineText")
    private String fineText;

    @ColumnInfo(name = "fineAmount")
    private String fineAmount;

    @ColumnInfo(name = "fineIsPayed")
    private boolean fineIsPayed;

    @ColumnInfo(name = "fineDate")
    private String fineDate;


    public Fines(String fineUuid, String carNumbersWithRegionCode, String fineText, String fineAmount, boolean fineIsPayed, String fineDate) {
        this.fineUuid = fineUuid;
        this.carNumbersWithRegionCode = carNumbersWithRegionCode;
        this.fineText = fineText;
        this.fineAmount = fineAmount;
        this.fineIsPayed = fineIsPayed;
        this.fineDate = fineDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFineUuid() {
        return fineUuid;
    }

    public int getId() {
        return id;
    }

    public String getCarNumbersWithRegionCode() {
        return carNumbersWithRegionCode;
    }

    public String getFineText() {
        return fineText;
    }

    public String getFineAmount() {
        return fineAmount;
    }

    public boolean isFineIsPayed() {
        return fineIsPayed;
    }

    public String getFineDate() {
        return fineDate;
    }
}
