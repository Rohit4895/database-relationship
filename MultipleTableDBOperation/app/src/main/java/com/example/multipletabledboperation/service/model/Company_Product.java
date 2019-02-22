package com.example.multipletabledboperation.service.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

@Entity(tableName = "Company_Product",
        primaryKeys = {"companyId","productId"},
        foreignKeys = {
               @ForeignKey(entity = Company.class,
                            parentColumns = "compId",
                            childColumns = "companyId"),
                @ForeignKey(entity = Products.class,
                             parentColumns = "prodId",
                             childColumns = "productId")
        })
public class Company_Product {
    @ColumnInfo(name = "companyId")
    private int companyId;

    @ColumnInfo(name = "productId")
    private int productId;

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
