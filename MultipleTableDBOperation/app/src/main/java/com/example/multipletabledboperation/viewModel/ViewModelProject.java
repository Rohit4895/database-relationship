package com.example.multipletabledboperation.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.multipletabledboperation.service.model.Company;
import com.example.multipletabledboperation.service.model.Products;
import com.example.multipletabledboperation.service.model.Projects;
import com.example.multipletabledboperation.viewModel.localRepository.DatabaseOperations;

import java.util.List;

public class ViewModelProject extends AndroidViewModel implements DatabaseOperations.SendProduct_ProjectData {

    private MutableLiveData<List<Projects>> listMutableLiveData;

    public ViewModelProject(@NonNull Application application) {
        super(application);
        this.listMutableLiveData = new MutableLiveData<>();
    }

    public LiveData<List<Projects>> getListOfProjectsData(int prodId){
        new DatabaseOperations(getApplication()).getAllProjectsForProducts(this,prodId);
        return listMutableLiveData;
    }


    @Override
    public void setLiveDataProducts(List<Products> liveData) {

    }

    @Override
    public void setLiveDataProjects(List<Projects> liveData) {
        listMutableLiveData.postValue(liveData);
    }
}
