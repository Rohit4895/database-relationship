package com.example.multipletabledboperation.service.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.multipletabledboperation.service.model.Company;
import com.example.multipletabledboperation.service.model.Company_Product;
import com.example.multipletabledboperation.service.model.Developer;
import com.example.multipletabledboperation.service.model.Product_Project;
import com.example.multipletabledboperation.service.model.Products;
import com.example.multipletabledboperation.service.model.Project_Technology;
import com.example.multipletabledboperation.service.model.Projects;
import com.example.multipletabledboperation.service.model.Technology;
import com.example.multipletabledboperation.service.model.Technology_Developer;
import com.example.multipletabledboperation.service.room.dao.CompanyDao;
import com.example.multipletabledboperation.service.room.dao.CompanyProductJoinDao;
import com.example.multipletabledboperation.service.room.dao.DeveloperDao;
import com.example.multipletabledboperation.service.room.dao.ProductDao;
import com.example.multipletabledboperation.service.room.dao.ProductProjectJoinDao;
import com.example.multipletabledboperation.service.room.dao.ProjectDao;
import com.example.multipletabledboperation.service.room.dao.ProjectTechnologyJoinDao;
import com.example.multipletabledboperation.service.room.dao.TechnologyDao;
import com.example.multipletabledboperation.service.room.dao.TechnologyDeveloperJoinDao;

@Database(entities = {Company.class, Products.class,
        Projects.class, Technology.class,
        Developer.class, Company_Product.class,
        Product_Project.class, Project_Technology.class,
        Technology_Developer.class},version = 1, exportSchema = false)

public abstract class CompanyDatabase extends RoomDatabase {

    public abstract CompanyDao companyDao();
    public abstract ProductDao productDao();
    public abstract ProjectDao projectDao();
    public abstract TechnologyDao technologyDao();
    public abstract DeveloperDao developerDao();
    public abstract CompanyProductJoinDao companyProductJoinDao();
    public abstract ProductProjectJoinDao productProjectJoinDao();
    public abstract ProjectTechnologyJoinDao projectTechnologyJoinDao();
    public abstract TechnologyDeveloperJoinDao technologyDeveloperJoinDao();
}
