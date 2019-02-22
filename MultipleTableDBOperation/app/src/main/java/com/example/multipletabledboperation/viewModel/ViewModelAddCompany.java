package com.example.multipletabledboperation.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.multipletabledboperation.service.model.Company;
import com.example.multipletabledboperation.viewModel.localRepository.DatabaseOperations;

import java.util.List;

public class ViewModelAddCompany extends AndroidViewModel implements DatabaseOperations.InsertOperation {

    private MutableLiveData<Boolean> listDataInsertCompany;

    public ViewModelAddCompany(@NonNull Application application) {
        super(application);
        listDataInsertCompany = new MutableLiveData<>();
    }

    public MutableLiveData<Boolean> insertCompany(Context context, String companyName){
        //This will give callback to SendCompanyData.setLiveData
        new DatabaseOperations(context).addCompany(this,companyName);
        return listDataInsertCompany;
    }


    @Override
    public void insertComplete(long id) {
        if(id == 0){
            //Insertion failed
            return;
        }
        listDataInsertCompany.postValue(true);

    }

    @Override
    public void insertComplete1(long id) {

    }
}
