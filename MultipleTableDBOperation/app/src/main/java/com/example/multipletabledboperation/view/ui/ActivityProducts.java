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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.multipletabledboperation.R;
import com.example.multipletabledboperation.service.model.Products;
import com.example.multipletabledboperation.service.model.Projects;
import com.example.multipletabledboperation.view.adapter.ProductAdapter;
import com.example.multipletabledboperation.viewModel.ViewModelProduct;

import java.util.ArrayList;
import java.util.List;

public class ActivityProducts extends AppCompatActivity implements ProductAdapter.AddProject {
    private ViewModelProduct viewModelProduct;
    private int compId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        compId = getIntent().getIntExtra("companyId",0);

        RecyclerView recyclerView = findViewById(R.id.productRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));

        final ProductAdapter productAdapter = new ProductAdapter(this, new ArrayList<Products>());
        recyclerView.setAdapter(productAdapter);

        viewModelProduct = ViewModelProviders.of(this).get(ViewModelProduct.class);

        viewModelProduct.getListOfProductData(compId).observe(ActivityProducts.this, new Observer<List<Products>>() {
            @Override
            public void onChanged(@Nullable List<Products> productsList) {
                Log.d("prodId", "ADD Activity: "+productsList);
                productAdapter.addItems(productsList);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_product, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(ActivityProducts.this, ActivityAddProduct.class);
        intent.putExtra("companyId",compId);
        startActivityForResult(intent,1);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        viewModelProduct.getListOfProductData(compId).observe(ActivityProducts.this, new Observer<List<Products>>() {
            @Override
            public void onChanged(@Nullable List<Products> productsList) {
                Log.d("prodId", "On Result Activity: "+productsList);
            }
        });
    }

    @Override
    public void goToAddProject(int id) {
        Intent intent = new Intent(this, ActivityProjects.class);
        intent.putExtra("productId",id);
        startActivity(intent);
    }
}
