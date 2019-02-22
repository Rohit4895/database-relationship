package com.example.multipletabledboperation.service.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Developer")
public class Developer {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "devId")
    private int devId;

    @ColumnInfo(name = "devName")
    private String devNAme;

    @ColumnInfo(name = "devAddress")
    private String devAddress;

    @ColumnInfo(name = "devMobile")
    private int devMobile;

    public int getDevId() {
        return devId;
    }

    public void setDevId(int devId) {
        this.devId = devId;
    }

    public String getDevNAme() {
        return devNAme;
    }

    public void setDevNAme(String devNAme) {
        this.devNAme = devNAme;
    }

    public String getDevAddress() {
        return devAddress;
    }

    public void setDevAddress(String devAddress) {
        this.devAddress = devAddress;
    }

    public int getDevMobile() {
        return devMobile;
    }

    public void setDevMobile(int devMobile) {
        this.devMobile = devMobile;
    }
}
