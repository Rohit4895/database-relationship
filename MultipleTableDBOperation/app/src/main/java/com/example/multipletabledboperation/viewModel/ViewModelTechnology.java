package com.example.multipletabledboperation.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.multipletabledboperation.service.model.Company;
import com.example.multipletabledboperation.service.model.Projects;
import com.example.multipletabledboperation.service.model.Technology;
import com.example.multipletabledboperation.viewModel.localRepository.DatabaseOperations;

import java.util.List;

public class ViewModelTechnology extends AndroidViewModel implements DatabaseOperations.SendProject_TechnologyData {

    private MutableLiveData<List<Technology>> listMutableLiveData;

    public ViewModelTechnology(@NonNull Application application) {
        super(application);
        listMutableLiveData = new MutableLiveData<>();
    }

    public LiveData<List<Technology>> getAllListOfTechnologyData(int projectId){
        new DatabaseOperations(getApplication()).getAllTechnologyForProjects(this,projectId);
        return listMutableLiveData;
    }


    @Override
    public void setLiveDataProjects(List<Projects> liveData) {

    }

    @Override
    public void setLiveDataTechnology(List<Technology> liveData) {
        listMutableLiveData.postValue(liveData);
    }
}
