package com.example.multipletabledboperation.service.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.multipletabledboperation.service.model.Projects;

import java.util.List;

@Dao
public interface ProjectDao {
    @Insert
    void insertProjects(Projects projects);

    @Update
    void updateProjects(Projects projects);

    @Query("DELETE FROM Projects")
    void deleteAllProjects();

    @Query("select * from Projects")
    List<Projects> getAllProjects();
}
