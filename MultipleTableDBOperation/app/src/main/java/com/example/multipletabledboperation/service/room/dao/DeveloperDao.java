package com.example.multipletabledboperation.service.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.multipletabledboperation.service.model.Developer;

import java.util.List;

@Dao
public interface DeveloperDao {
    @Insert
    void insertDeveloper(Developer developer);

    @Update
    void updateDeveloper(Developer developer);

    @Query("DELETE FROM Developer")
    void deleteAllDeveloper();

    @Query("select * from Developer")
    List<Developer> getAllDeveloper();
}
