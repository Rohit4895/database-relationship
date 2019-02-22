package com.example.multipletabledboperation.service.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.RoomWarnings;

import com.example.multipletabledboperation.service.model.Company;
import com.example.multipletabledboperation.service.model.Company_Product;
import com.example.multipletabledboperation.service.model.Products;

import java.util.List;

@Dao
public interface CompanyProductJoinDao {

    @Insert
    long insertCD(Company_Product company_product);

    @Query("select * from Company INNER JOIN Company_Product " +
            "ON Company.compId = Company_Product.companyId " +
            "where Company_Product.productId =:prodId")
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    List<Company> getCompForProd(final int prodId);

    @Query("select * from Products INNER JOIN Company_Product " +
            "ON Products.prodId = Company_Product.productId " +
            "where Company_Product.companyId =:compId")
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    List<Products> getProdForComp(final int compId);

    @Query("select prodId from Products where prodName=:prodName")
    int getProdId(String prodName);

    @Query("select productId from Company_Product where companyId=:companyId")
    List<Integer> getProdIdList(int companyId);
}
