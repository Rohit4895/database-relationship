package com.example.multipletabledboperation.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.multipletabledboperation.viewModel.localRepository.DatabaseOperations;

public class ViewModelAddProject extends AndroidViewModel implements DatabaseOperations.InsertOperation, DatabaseOperations.SendId {
    private MutableLiveData<Boolean> insertProject;
    private MutableLiveData<Integer> getProjectId;
    private MutableLiveData<Boolean> insertProd_Proj;

    public ViewModelAddProject(@NonNull Application application) {
        super(application);
        insertProject = new MutableLiveData<>();
        getProjectId = new MutableLiveData<>();
        insertProd_Proj = new MutableLiveData<>();
    }

    public LiveData<Boolean> insertProject(String projectName){
        new DatabaseOperations(getApplication()).addProject(this,projectName);
        return insertProject;
    }

    public LiveData<Integer> getProjectId(String projectName){
        new DatabaseOperations(getApplication()).getProjectId(this,projectName);
        return getProjectId;
    }

    public LiveData<Boolean> insertProductAndProjectId(int productId, int projectId){
        new DatabaseOperations(getApplication()).insertProduct_Project(this,productId,projectId);
        return insertProd_Proj;
    }

    @Override
    public void insertComplete(long id) {
        if (id == 0){
            return;
        }
        insertProject.postValue(true);
    }

    @Override
    public void insertComplete1(long id) {
        if (id == 0){
            return;
        }
        insertProd_Proj.postValue(true);
    }

    @Override
    public void getId(int id) {
        if (id == 0){
            return;
        }
        getProjectId.postValue(id);
    }
}
