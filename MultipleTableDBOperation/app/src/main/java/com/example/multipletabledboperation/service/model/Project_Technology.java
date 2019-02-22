package com.example.multipletabledboperation.service.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

@Entity(tableName = "Project_Technology",
        primaryKeys = {"projectId","technologyId"},
        foreignKeys = {
                @ForeignKey(entity = Projects.class,
                        parentColumns = "proId",
                        childColumns = "projectId"),
                @ForeignKey(entity = Technology.class,
                        parentColumns = "techId",
                        childColumns = "technologyId")
        })
public class Project_Technology {
    @ColumnInfo(name = "projectId")
    private int projectId;

    @ColumnInfo(name = "technologyId")
    private int technologyId;

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getTechnologyId() {
        return technologyId;
    }

    public void setTechnologyId(int technologyId) {
        this.technologyId = technologyId;
    }
}
