package com.example.multipletabledboperation.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.multipletabledboperation.service.model.Company;
import com.example.multipletabledboperation.viewModel.localRepository.DatabaseOperations;

import java.util.List;

public class ViewModelCompany extends AndroidViewModel implements DatabaseOperations.InsertOperation,
        DatabaseOperations.SendCompanyData {

    private MutableLiveData<List<Company>> listMutableLiveData;

    public ViewModelCompany(@NonNull Application application) {
        super(application);
        this.listMutableLiveData = new MutableLiveData<>();
    }

    public LiveData<List<Company>> getCompanyData(){
        new DatabaseOperations(getApplication()).getAllCompanies(this);
        return listMutableLiveData;
    }

    @Override
    public void setLiveData(List<Company> liveData) {
        Log.d("list", "LiveData: "+liveData.size());
        listMutableLiveData.postValue(liveData);

    }

    @Override
    public void insertComplete(long id) {
        Log.d("list", "LiveData: "+id);
        new DatabaseOperations(getApplication()).getAllCompanies(this);
    }

    @Override
    public void insertComplete1(long id) {

    }
}
