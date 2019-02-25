package com.example.multipletabledboperation.view.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.multipletabledboperation.R;
import com.example.multipletabledboperation.service.model.Developer;
import com.example.multipletabledboperation.service.model.Projects;
import com.example.multipletabledboperation.view.adapter.DeveloperAdapter;
import com.example.multipletabledboperation.view.adapter.ProjectAdapter;
import com.example.multipletabledboperation.viewModel.ViewModelDeveloper;
import com.example.multipletabledboperation.viewModel.ViewModelProject;
import com.example.multipletabledboperation.viewModel.localRepository.DatabaseOperations;

import java.util.ArrayList;
import java.util.List;

public class ActivityDeveloper extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ViewModelDeveloper viewModelDeveloper;
    private int technologyId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);

        technologyId = getIntent().getIntExtra("technologyId",0);

        recyclerView = findViewById(R.id.developereRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        final DeveloperAdapter developerAdapter = new DeveloperAdapter(this, new ArrayList<Developer>());
        recyclerView.setAdapter(developerAdapter);

        viewModelDeveloper = ViewModelProviders.of(this).get(ViewModelDeveloper.class);

        LiveData<List<Developer>> developerList = viewModelDeveloper.getListOfDevelopersData(technologyId);
        developerList.observe(ActivityDeveloper.this, new Observer<List<Developer>>() {
            @Override
            public void onChanged(@Nullable List<Developer> developers) {
                developerAdapter.addItems(developers);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_developer,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this, ActivityAddDeveloper.class);
        intent.putExtra("technologyId",technologyId);
        startActivityForResult(intent,1);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        viewModelDeveloper.getListOfDevelopersData(technologyId).observe(ActivityDeveloper.this, new Observer<List<Developer>>() {
            @Override
            public void onChanged(@Nullable List<Developer> developers) {

            }
        });
    }
}
