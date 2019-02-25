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
import com.example.multipletabledboperation.service.model.Technology;
import com.example.multipletabledboperation.view.adapter.TechnologyAdapter;
import com.example.multipletabledboperation.viewModel.ViewModelTechnology;

import java.util.ArrayList;
import java.util.List;

public class ActivityTechnology extends AppCompatActivity implements TechnologyAdapter.AddDeveloper {
    private RecyclerView recyclerView;
    private ViewModelTechnology viewModelTechnology;
    private  int projectId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technology);

        viewModelTechnology = ViewModelProviders.of(this).get(ViewModelTechnology.class);
        projectId = getIntent().getIntExtra("projectId",0);

        recyclerView = findViewById(R.id.technologyRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        final TechnologyAdapter technologyAdapter = new TechnologyAdapter(this,new ArrayList<Technology>());
        recyclerView.setAdapter(technologyAdapter);

        LiveData<List<Technology>> listLiveData = viewModelTechnology.getAllListOfTechnologyData(projectId);
        listLiveData.observe(ActivityTechnology.this, new Observer<List<Technology>>() {
            @Override
            public void onChanged(@Nullable List<Technology> technologyList) {
                technologyAdapter.addItems(technologyList);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_technology,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this,ActivityAddTechnology.class);
        intent.putExtra("projectId", projectId);
        startActivityForResult(intent,1);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        viewModelTechnology.getAllListOfTechnologyData(projectId).observe(ActivityTechnology.this, new Observer<List<Technology>>() {
            @Override
            public void onChanged(@Nullable List<Technology> technologyList) {

            }
        });
    }

    @Override
    public void goToAddDeveloper(int technologyId) {
        Intent intent = new Intent(this,ActivityDeveloper.class);
        intent.putExtra("technologyId", technologyId);
        startActivity(intent);
    }
}
