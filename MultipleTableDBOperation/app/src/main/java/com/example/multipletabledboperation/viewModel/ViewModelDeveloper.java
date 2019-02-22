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
import com.example.multipletabledboperation.viewModel.localRepository.DatabaseOperations;

import java.util.List;

public class ViewModelDeveloper extends AndroidViewModel implements DatabaseOperations.SendDeveloperData {

    private MutableLiveData<List<Developer>> listMutableLiveData;

    public ViewModelDeveloper(@NonNull Application application) {
        super(application);
        this.listMutableLiveData = new MutableLiveData<>();
    }

    public LiveData<List<Developer>> getCompanyData(){
        return listMutableLiveData;
    }

    public void insertDeveloper(Context context, String s){
        new DatabaseOperations(context).addDeveloper(this,s);
    }

    @Override
    public void setLiveData(List<Developer> liveData) {
        listMutableLiveData.postValue(liveData);
        Log.d("list", "LiveData: "+liveData);
    }
}
