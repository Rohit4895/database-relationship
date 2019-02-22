/*
package com.example.multipletabledboperation.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.multipletabledboperation.service.model.Company;
import com.example.multipletabledboperation.service.model.Company_Product;
import com.example.multipletabledboperation.service.model.Products;
import com.example.multipletabledboperation.viewModel.localRepository.DatabaseOperations;

import java.util.List;

public class ViewModelCompanyProduct extends AndroidViewModel implements DatabaseOperations.SendCompany_ProductData,
        DatabaseOperations.InsertOperation, DatabaseOperations.SendProdId {
    private MutableLiveData<List<Company_Product>> listMutableLiveData;
    private MutableLiveData<Integer> intMutableLiveData;

    public ViewModelCompanyProduct(@NonNull Application application) {
        super(application);
        listMutableLiveData = new MutableLiveData<>();
        intMutableLiveData = new MutableLiveData<>();
    }

    public LiveData<List<Company_Product>> getCompany_ProductData(int companyId){
        new DatabaseOperations(getApplication()).getAllProductIdForCompany(this, companyId);
        return listMutableLiveData;
    }

    public void insertCompany_Product(int compId, int prodId){
        new DatabaseOperations(getApplication()).addCompany_Product(this,compId,prodId);
    }

    public LiveData<Integer> getProductId(String prodName){
        new DatabaseOperations(getApplication()).getProductId(this,prodName);
        return intMutableLiveData;
    }

    @Override
    public void setLiveDataProduct(List<Products> liveData) {

    }

    @Override
    public void setLiveDataCompany(List<Company> liveData) {

    }

    @Override
    public void insertComplete(long id) {
        if (id == 0){
            return;
        }

        new DatabaseOperations(getApplication()).getAllProductIdForCompany(this, );
    }

    @Override
    public void prodId(int id) {
        if (id == 0){
            return;
        }
        intMutableLiveData.postValue(id);

    }
}
*/
