package com.example.multipletabledboperation.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.multipletabledboperation.viewModel.localRepository.DatabaseOperations;

public class ViewModelAddDeveloper extends AndroidViewModel implements DatabaseOperations.InsertOperation, DatabaseOperations.SendId {
  private MutableLiveData<Boolean> insertDeveloper;
  private MutableLiveData<Integer> getDeveloperId;
  private MutableLiveData<Boolean> insertTech_Dev;

    public ViewModelAddDeveloper(@NonNull Application application) {
        super(application);
        insertDeveloper = new MutableLiveData<>();
        getDeveloperId = new MutableLiveData<>();
        insertTech_Dev = new MutableLiveData<>();
        }

    public LiveData<Boolean> insertDeveloper(String developerName, String developerAdd, int developerMob){
        new DatabaseOperations(getApplication()).addDeveloper(this,developerName,developerAdd,developerMob);
        return insertDeveloper;
        }

   public LiveData<Integer> getDveloperId(String developerName){
        new DatabaseOperations(getApplication()).getDeveloperId(this,developerName);
        return getDeveloperId;
        }

   public LiveData<Boolean> insertTechnologyAndDeveloperId(int technologyId, int developerId){
        new DatabaseOperations(getApplication()).insertTechnology_Developer(this,technologyId,developerId);
        return insertTech_Dev;
        }

   @Override
    public void insertComplete(long id) {
        if (id == 0){
        return;
        }
       insertDeveloper.postValue(true);
        }

    @Override
   public void insertComplete1(long id) {
        if (id == 0){
        return;
        }
        insertTech_Dev.postValue(true);
        }

   @Override
   public void getId(int id) {
        if (id == 0){
        return;
        }
       getDeveloperId.postValue(id);
        }
        }
