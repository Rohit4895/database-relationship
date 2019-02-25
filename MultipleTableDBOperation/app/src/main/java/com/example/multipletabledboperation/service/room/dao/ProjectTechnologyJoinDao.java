package com.example.multipletabledboperation.service.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.RoomWarnings;

import com.example.multipletabledboperation.service.model.Project_Technology;
import com.example.multipletabledboperation.service.model.Projects;
import com.example.multipletabledboperation.service.model.Technology;

import java.util.List;

@Dao
public interface ProjectTechnologyJoinDao {
    @Insert
    long insertPT(Project_Technology project_technology);

    @Query("select * from Projects INNER JOIN Project_Technology " +
            "ON Projects.proId = Project_Technology.projectId " +
            "where Project_Technology.technologyId=:techId")
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    List<Projects> getProjForTech(final int techId);

    @Query("select * from Technology INNER JOIN Project_Technology " +
            "ON Technology.techId = Project_Technology.technologyId " +
            "where Project_Technology.projectId =:proId")
    List<Technology> getTechForProj(final int proId);

    @Query("select techId from Technology where techName=:technologyName")
    int getTechnologyId(String technologyName);
}
