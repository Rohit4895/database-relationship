package com.example.multipletabledboperation.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.multipletabledboperation.service.model.Company;
import com.example.multipletabledboperation.service.model.Technology;
import com.example.multipletabledboperation.viewModel.localRepository.DatabaseOperations;

import java.util.List;

public class ViewModelTechnology extends AndroidViewModel implements DatabaseOperations.SendTechnologyData {

    private MutableLiveData<List<Technology>> listMutableLiveData;

    public ViewModelTechnology(@NonNull Application application) {
        super(application);
        this.listMutableLiveData = new MutableLiveData<>();
    }

    public LiveData<List<Technology>> getCompanyData(){
        return listMutableLiveData;
    }

    public void insertTechnology(Context context, String s){
        new DatabaseOperations(context).addTechnology(this,s);
    }

    @Override
    public void setLiveData(List<Technology> liveData) {
        listMutableLiveData.postValue(liveData);
        Log.d("list", "LiveData: "+liveData);
    }
}
