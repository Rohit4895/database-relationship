package com.example.multipletabledboperation.service.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.multipletabledboperation.service.model.Company;
import java.util.List;

@Dao
public interface CompanyDao {

    @Insert
    long insertCompany(Company company);

    @Update
    void updateCompany(Company company);

    @Query("DELETE FROM Company")
    void deleteAllCompany();

    @Query("select * from Company")
    List<Company> getAllCompany();

}
