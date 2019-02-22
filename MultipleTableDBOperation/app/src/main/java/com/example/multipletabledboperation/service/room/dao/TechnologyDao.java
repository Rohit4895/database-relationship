package com.example.multipletabledboperation.service.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.multipletabledboperation.service.model.Technology;

import java.util.List;

@Dao
public interface TechnologyDao {
    @Insert
    void insertTechnology(Technology technology);

    @Update
    void updateTechnology(Technology technology);

    @Query("DELETE FROM Technology")
    void deleteAllTechnology();

    @Query("select * from Technology")
    List<Technology> getAllTechnology();
}
