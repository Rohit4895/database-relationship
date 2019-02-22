package com.example.multipletabledboperation.service.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

@Entity(tableName = "Technology_Developer",
        primaryKeys = {"technologyId","developerId"},
        foreignKeys = {
                @ForeignKey(entity = Technology.class,
                        parentColumns = "techId",
                        childColumns = "technologyId"),
                @ForeignKey(entity = Developer.class,
                        parentColumns = "devId",
                        childColumns = "developerId")
        })
public class Technology_Developer {

    @ColumnInfo(name = "technologyId")
    private int technologyId;

    @ColumnInfo(name = "developerId")
    private int developerId;

    public int getTechnologyId() {
        return technologyId;
    }

    public void setTechnologyId(int technologyId) {
        this.technologyId = technologyId;
    }

    public int getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(int developerId) {
        this.developerId = developerId;
    }
}
