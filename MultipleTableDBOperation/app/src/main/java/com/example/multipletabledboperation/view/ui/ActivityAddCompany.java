package com.example.multipletabledboperation.view.ui;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.multipletabledboperation.R;
import com.example.multipletabledboperation.viewModel.ViewModelAddCompany;

public class ActivityAddCompany extends AppCompatActivity {

    private Button save;
    private EditText editText;
    private ViewModelAddCompany viewModelAddCompany;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        save = findViewById(R.id.compSaveButton);
        editText = findViewById(R.id.compEditText);
        viewModelAddCompany = ViewModelProviders.of(this).get(ViewModelAddCompany.class);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String strCompanyName = editText.getText().toString();

                LiveData<Boolean> liveBoolean = viewModelAddCompany.insertCompany(ActivityAddCompany.this, strCompanyName);
                liveBoolean.observe(ActivityAddCompany.this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(@Nullable Boolean insertSuccess) {
                        Log.d("WASTE","Insert: "+ insertSuccess);

                        if(!insertSuccess){
                            Toast.makeText(ActivityAddCompany.this,
                                    "Error while insertion",
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }
                        setResult(Activity.RESULT_OK, null);
                        finish();
                    }
                });
            }
        });
    }
}
