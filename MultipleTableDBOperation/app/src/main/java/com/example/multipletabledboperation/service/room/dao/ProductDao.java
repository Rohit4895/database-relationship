package com.example.multipletabledboperation.service.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.multipletabledboperation.service.model.Products;

import java.util.List;

@Dao
public interface ProductDao {
    @Insert
    long insertProducts(Products products);

    @Update
    void updateProducts(Products products);

    @Query("DELETE FROM Products")
    void deleteAllProducts();

    @Query("select * from Products")
    List<Products> getAllProducts();

}
