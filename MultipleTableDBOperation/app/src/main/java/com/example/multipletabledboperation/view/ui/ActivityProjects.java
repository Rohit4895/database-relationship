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
import com.example.multipletabledboperation.service.model.Projects;
import com.example.multipletabledboperation.service.model.Technology;
import com.example.multipletabledboperation.view.adapter.ProductAdapter;
import com.example.multipletabledboperation.view.adapter.ProjectAdapter;
import com.example.multipletabledboperation.viewModel.ViewModelProject;

import java.util.ArrayList;
import java.util.List;

public class ActivityProjects extends AppCompatActivity implements ProjectAdapter.AddTechnology {
    private RecyclerView recyclerView;
    private ViewModelProject viewModelProject;
    private int productId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);

        productId = getIntent().getIntExtra("productId",0);

        recyclerView = findViewById(R.id.projectRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        final ProjectAdapter projectAdapter = new ProjectAdapter(this, new ArrayList<Projects>());
        recyclerView.setAdapter(projectAdapter);

        viewModelProject = ViewModelProviders.of(this).get(ViewModelProject.class);

        LiveData<List<Projects>> projectsList = viewModelProject.getListOfProjectsData(productId);
        projectsList.observe(ActivityProjects.this, new Observer<List<Projects>>() {
            @Override
            public void onChanged(@Nullable List<Projects> projects) {
                projectAdapter.addItems(projects);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_project,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(ActivityProjects.this, ActivityAddProject.class);
        intent.putExtra("productId",productId);
        startActivityForResult(intent,1);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        viewModelProject.getListOfProjectsData(productId).observe(ActivityProjects.this, new Observer<List<Projects>>() {
            @Override
            public void onChanged(@Nullable List<Projects> projects) {

            }
        });
    }


    @Override
    public void goToAddTechnology(int projectId) {
        Intent intent = new Intent(this, ActivityTechnology.class);
        intent.putExtra("projectId",projectId);
        startActivity(intent);
    }
}
