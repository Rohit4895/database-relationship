package com.example.multipletabledboperation.service.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

@Entity(tableName = "Product_Project",
        primaryKeys = {"productId","projectId"},
        foreignKeys = {
                @ForeignKey(entity = Products.class,
                        parentColumns = "prodId",
                        childColumns = "productId"),
                @ForeignKey(entity = Projects.class,
                        parentColumns = "proId",
                        childColumns = "projectId")
        })
public class Product_Project {

    @ColumnInfo(name = "productId")
    private int productId;

    @ColumnInfo(name = "projectId")
    private int projectId;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}
