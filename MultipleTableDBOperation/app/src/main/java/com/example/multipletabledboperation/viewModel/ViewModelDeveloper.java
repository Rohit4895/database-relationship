package com.example.multipletabledboperation.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.multipletabledboperation.service.model.Company;
import com.example.multipletabledboperation.service.model.Developer;
import com.example.multipletabledboperation.service.model.Products;
import com.example.multipletabledboperation.service.model.Projects;
import com.example.multipletabledboperation.service.model.Technology;
import com.example.multipletabledboperation.viewModel.localRepository.DatabaseOperations;

import java.util.List;

public class ViewModelDeveloper extends AndroidViewModel implements DatabaseOperations.SendTechnology_DeveloperData {

    private MutableLiveData<List<Developer>> listMutableLiveData;

    public ViewModelDeveloper(@NonNull Application application) {
        super(application);
        this.listMutableLiveData = new MutableLiveData<>();
    }

    public LiveData<List<Developer>> getListOfDevelopersData(int techId){
        new DatabaseOperations(getApplication()).getAllDeveloperForTechnology(this,techId);
        return listMutableLiveData;
    }

    @Override
    public void setLiveDataTechnology(List<Technology> liveData) {

    }

    @Override
    public void setLiveDataDeveloper(List<Developer> liveData) {
        listMutableLiveData.postValue(liveData);
    }
}
