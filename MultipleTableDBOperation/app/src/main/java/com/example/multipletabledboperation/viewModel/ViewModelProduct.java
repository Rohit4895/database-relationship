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
import com.example.multipletabledboperation.viewModel.localRepository.DatabaseOperations;

import java.util.List;

public class ViewModelProduct extends AndroidViewModel implements DatabaseOperations.SendCompany_ProductData {

    private MutableLiveData<List<Products>> listMutableLiveData;
    private int compId;

    public ViewModelProduct(@NonNull Application application) {
        super(application);
        this.listMutableLiveData = new MutableLiveData<>();

    }

    public LiveData<List<Products>> getListOfProductData(int compId){
        this.compId = compId;
        new DatabaseOperations(getApplication()).getAllProductForCompany(this,compId);
        return listMutableLiveData;
    }


    @Override
    public void setLiveDataProducts(List<Products> liveData) {
        Log.d("prodId", "Product setLiveDataProducts: "+liveData);
        listMutableLiveData.postValue(liveData);
    }


    @Override
    public void setLiveDataCompany(List<Company> liveData) {

    }


}
