package com.example.multipletabledboperation.view.ui;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.multipletabledboperation.R;
import com.example.multipletabledboperation.service.model.Products;
import com.example.multipletabledboperation.viewModel.ViewModelAddProduct;

import java.util.List;

public class ActivityAddProduct extends AppCompatActivity {
    private TextView textView;
    private EditText editText;
    private Button button;
    private ViewModelAddProduct viewModelAddProduct;
    //private ViewModelCompanyProduct viewModelCompanyProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        textView = findViewById(R.id.compForProd);
        editText = findViewById(R.id.prodEditText);
        button = findViewById(R.id.prodSaveButton);

       viewModelAddProduct = ViewModelProviders.of(this).get(ViewModelAddProduct.class);
      // viewModelCompanyProduct = ViewModelProviders.of(this).get(ViewModelCompanyProduct.class);

        final int compId = getIntent().getIntExtra("companyId",0);
        textView.setText("Company Id: "+compId);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String prodName = editText.getText().toString();

                LiveData<Boolean> booleanLiveData = viewModelAddProduct.insertProduct(ActivityAddProduct.this, prodName);
                booleanLiveData.observe(ActivityAddProduct.this, new Observer<Boolean>() {

                    @Override
                    public void onChanged(@Nullable Boolean aBoolean) {
                       final LiveData<Integer> prodId = viewModelAddProduct.getProductId(prodName);
                       prodId.observe(ActivityAddProduct.this, new Observer<Integer>() {

                           @Override
                           public void onChanged(@Nullable final Integer integer) {
                               Integer i = new Integer(integer);
                               final int id = i.intValue();
                               Log.d("prodId", "prodId: "+id+" compId: "+compId);
                              LiveData<Boolean> comProd = viewModelAddProduct.insertCompany_Product(compId,id);
                              comProd.observe(ActivityAddProduct.this, new Observer<Boolean>() {
                                  @Override
                                  public void onChanged(@Nullable Boolean aBoolean) {
                                      LiveData<List<Products>> listLiveData = viewModelAddProduct.getListOfProductData(compId);
                                      listLiveData.observe(ActivityAddProduct.this, new Observer<List<Products>>() {
                                          @Override
                                          public void onChanged(@Nullable List<Products> productsList) {
                                              setResult(Activity.RESULT_OK, null);
                                              finish();
                                          }
                                      });
                                  }
                              });
                           }
                       });

                        setResult(Activity.RESULT_OK, null);
                        finish();
                    }
                });
            }
        });
    }
}
