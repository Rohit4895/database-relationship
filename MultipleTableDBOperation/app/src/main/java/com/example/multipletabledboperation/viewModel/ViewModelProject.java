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
import com.example.multipletabledboperation.viewModel.localRepository.DatabaseOperations;

import java.util.List;

public class ViewModelProject extends AndroidViewModel implements DatabaseOperations.SendProjectData {

    private MutableLiveData<List<Projects>> listMutableLiveData;

    public ViewModelProject(@NonNull Application application) {
        super(application);
        this.listMutableLiveData = new MutableLiveData<>();
    }

    public LiveData<List<Projects>> getCompanyData(){
        return listMutableLiveData;
    }

    public void insertProjects(Context context, String s){
        new DatabaseOperations(context).addProject(this,s);
    }

    @Override
    public void setLiveData(List<Projects> liveData) {
        listMutableLiveData.postValue(liveData);
        Log.d("list", "LiveData: "+liveData);
    }
}
