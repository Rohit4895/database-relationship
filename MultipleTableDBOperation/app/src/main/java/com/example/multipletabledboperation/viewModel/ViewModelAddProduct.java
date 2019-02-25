package com.example.multipletabledboperation.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.multipletabledboperation.service.model.Company;
import com.example.multipletabledboperation.service.model.Company_Product;
import com.example.multipletabledboperation.service.model.Products;
import com.example.multipletabledboperation.viewModel.localRepository.DatabaseOperations;

import java.util.List;

public class ViewModelAddProduct extends AndroidViewModel implements DatabaseOperations.InsertOperation, DatabaseOperations.SendId {

    private MutableLiveData<Boolean> booleanMutableLiveData;
    private MutableLiveData<Boolean> booleanMutableLiveData1;
    private MutableLiveData<Integer> intMutableLiveData;

    public ViewModelAddProduct(@NonNull Application application) {
        super(application);
        booleanMutableLiveData = new MutableLiveData<>();
        booleanMutableLiveData1 = new MutableLiveData<>();
        intMutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<Boolean> insertProduct(Context context, String prodName){
       new DatabaseOperations(context).addProduct(this, prodName);
        return booleanMutableLiveData;
    }

    public LiveData<Integer> getProductId(String prodName){
        new DatabaseOperations(getApplication()).getProductId(this,prodName);
        return intMutableLiveData;
    }

    public MutableLiveData<Boolean> insertCompany_Product(int compId, int prodId){
        new DatabaseOperations(getApplication()).addCompany_Product(this,compId,prodId);
        return booleanMutableLiveData1;
    }


    @Override
    public void insertComplete(long id) {
        Log.d("prodId", "Add Product insertComplete: "+id);
        if(id == 0){
            return;
        }
        booleanMutableLiveData.postValue(true);
    }

    @Override
    public void insertComplete1(long id) {
        Log.d("prodId", "Add Product insertComplete 1: "+id);
        if (id == 0){
            return;
        }
        booleanMutableLiveData1.postValue(true);
    }

    @Override
    public void getId(int id) {
        Log.d("prodId", "Add Product prodId: "+id);
        if (id == 0){
            return;
        }
        intMutableLiveData.postValue(id);
    }
}
