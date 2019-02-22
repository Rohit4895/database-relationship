package com.example.multipletabledboperation.service.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Projects")
public class Projects {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "proId")
    private int proId;

    @ColumnInfo(name = "proName")
    private String proName;

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

}
