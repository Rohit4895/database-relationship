package com.example.multipletabledboperation.service.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.RoomWarnings;

import com.example.multipletabledboperation.service.model.Product_Project;
import com.example.multipletabledboperation.service.model.Products;
import com.example.multipletabledboperation.service.model.Projects;

import java.util.List;

@Dao
public interface ProductProjectJoinDao {
    @Insert
    void insertPP(Product_Project product_project);

    @Query("select * from Products INNER JOIN Product_Project " +
            "ON Products.prodId = Product_Project.productId " +
            "where Product_Project.projectId =:proId")
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    List<Products> getProdForProj(final int proId);

    @Query("select * from Projects INNER JOIN Product_Project " +
            "ON Projects.proId = Product_Project.projectId " +
            "where Product_Project.productId =:prodId")
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    List<Projects> getProjForProd(final int prodId);
}
