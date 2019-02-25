package com.example.multipletabledboperation.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.multipletabledboperation.viewModel.localRepository.DatabaseOperations;

public class ViewModelAddTechnology extends AndroidViewModel implements DatabaseOperations.InsertOperation,
        DatabaseOperations.SendId {
    private MutableLiveData<Boolean> insertTechnology;
    private MutableLiveData<Integer> getTechId;
    private MutableLiveData<Boolean> insertID;

    public ViewModelAddTechnology(@NonNull Application application) {
        super(application);
        insertTechnology = new MutableLiveData<>();
        getTechId = new MutableLiveData<>();
        insertID = new MutableLiveData<>();
    }

    public LiveData<Boolean> insertTechnology(String technologyName){
        new DatabaseOperations(getApplication()).addTechnology(this,technologyName);
        return insertTechnology;
    }

    public LiveData<Integer> getTechnologyId(String technologyName){
        new DatabaseOperations(getApplication()).getTechnologyId(this,technologyName);
        return getTechId;
    }

    public LiveData<Boolean> insertProjectAndTechnologyId(int projectId, int technologyId){
        new DatabaseOperations(getApplication()).insertProject_Technology(this,projectId,technologyId);
        return insertID;
    }

    @Override
    public void insertComplete(long id) {
        insertTechnology.postValue(true);
    }

    @Override
    public void insertComplete1(long id) {
        insertID.postValue(true);
    }

    @Override
    public void getId(int id) {
        getTechId.postValue(id);
    }
}
