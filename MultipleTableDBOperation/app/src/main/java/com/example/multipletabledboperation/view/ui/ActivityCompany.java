package com.example.multipletabledboperation.view.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import android.arch.lifecycle.ViewModelProviders;

import com.example.multipletabledboperation.R;
import com.example.multipletabledboperation.service.model.Company;
import com.example.multipletabledboperation.service.model.Products;
import com.example.multipletabledboperation.view.adapter.CompanyAdapter;
import com.example.multipletabledboperation.viewModel.ViewModelCompany;

import java.util.ArrayList;
import java.util.List;

public class ActivityCompany extends AppCompatActivity implements CompanyAdapter.AddProd {

    private ViewModelCompany companyViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.companyRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        final CompanyAdapter companyAdapter = new CompanyAdapter(this, new ArrayList<Company>());
        recyclerView.setAdapter(companyAdapter);

        companyViewModel = ViewModelProviders.of(this).get(ViewModelCompany.class);

        LiveData<List<Company>> listLiveData = companyViewModel.getCompanyData();

        listLiveData.observe(ActivityCompany.this, new Observer<List<Company>>() {
            @Override
            public void onChanged(@Nullable List<Company> list) {
                Log.d("list","onActivityResult: "+list.size());
                companyAdapter.addItems(list);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_company,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id){
            case R.id.addCompany:
                Toast.makeText(getApplicationContext(),"AddCompany", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ActivityCompany.this, ActivityAddCompany.class);
                startActivityForResult(intent,1);
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

            companyViewModel.getCompanyData().observe(this, new Observer<List<Company>>() {
                @Override
                public void onChanged(@Nullable List<Company> list) {
                    Log.d("WASTE","list: "+list.size());
                }
            });


    }

    @Override
    public void goToAddProd(int id) {
        Intent intent = new Intent(ActivityCompany.this, ActivityProducts.class);
        intent.putExtra("companyId", id);
        Toast.makeText(getApplicationContext(),"Id: "+id, Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }
}
