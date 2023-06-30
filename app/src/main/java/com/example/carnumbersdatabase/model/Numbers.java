package com.example.carnumbersdatabase.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "numbers")
public class Numbers implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id = 0;

    @ColumnInfo(name = "personName")
    private String personName;

    @ColumnInfo(name = "personLastname")
    private String personLastname;

    @ColumnInfo(name = "personBirthdate")
    private String personBirthdate;

    @ColumnInfo(name="personAddress")
    private String personAddress;

    @ColumnInfo(name="personTransportName")
    private String personTransportName;

    @ColumnInfo(name="personCarNumbers")
    private String personCarNumbers;

    @ColumnInfo(name="personRegionCode")
    private int personRegionCode;

    @ColumnInfo(name="personPhoneNumber")
    private String personPhoneNumber;

    @ColumnInfo(name="personHasLicense")
    private boolean personHasLicense;

    public Numbers(String personName, String personLastname, String personBirthdate, String personAddress, String personTransportName, String personCarNumbers, int personRegionCode, String personPhoneNumber, boolean personHasLicense) {
        this.personName = personName;
        this.personLastname = personLastname;
        this.personBirthdate = personBirthdate;
        this.personAddress = personAddress;
        this.personTransportName = personTransportName;
        this.personCarNumbers = personCarNumbers;
        this.personRegionCode = personRegionCode;
        this.personPhoneNumber = personPhoneNumber;
        this.personHasLicense = personHasLicense;
    }


    public boolean isPersonHasLicense() {
        return personHasLicense;
    }

    public int getId() {
        return id;
    }

    public String getPersonName() {
        return personName;
    }

    public String getPersonLastname() {
        return personLastname;
    }

    public String getPersonBirthdate() {
        return personBirthdate;
    }

    public String getPersonAddress() {
        return personAddress;
    }

    public String getPersonTransportName() {
        return personTransportName;
    }

    public String getPersonCarNumbers() {
        return personCarNumbers;
    }

    public int getPersonRegionCode() {
        return personRegionCode;
    }

    public String getPersonPhoneNumber() {
        return personPhoneNumber;
    }

    public void setId(int id) {
        this.id = id;
    }
}
