package com.example.multipletabledboperation.service.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.RoomWarnings;

import com.example.multipletabledboperation.service.model.Developer;
import com.example.multipletabledboperation.service.model.Technology;
import com.example.multipletabledboperation.service.model.Technology_Developer;

import java.util.List;

@Dao
public interface TechnologyDeveloperJoinDao {

    @Insert
    long insertTD(Technology_Developer technology_developer);

    @Query("select * from Technology INNER JOIN Technology_Developer " +
            "ON Technology.techId = Technology_Developer.technologyId " +
            "where Technology_Developer.developerId=:devId")
    List<Technology> getTechForDev(final int devId);

    @Query("select * from Developer INNER JOIN Technology_Developer " +
            "ON Developer.devId = Technology_Developer.developerId " +
            "where Technology_Developer.technologyId=:techId")
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    List<Developer> getDevForTech(final int techId);

    @Query("select devId from Developer where devName=:developerName")
    int getDeveloperId(String developerName);
}
