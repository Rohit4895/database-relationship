package com.example.multipletabledboperation.view.ui;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.multipletabledboperation.R;
import com.example.multipletabledboperation.service.model.Technology;
import com.example.multipletabledboperation.viewModel.ViewModelAddTechnology;

import java.util.List;

public class ActivityAddTechnology extends AppCompatActivity {
    private TextView textView;
    private EditText editText;
    private Button button;
    private ViewModelAddTechnology viewModelAddTechnology;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_technology);

         final int projectId = getIntent().getIntExtra("projectId",0);
         textView = findViewById(R.id.projectId);
         editText = findViewById(R.id.techEditText);
         button = findViewById(R.id.techSaveButton);
         viewModelAddTechnology = ViewModelProviders.of(this).get(ViewModelAddTechnology.class);

         textView.setText("Project ID: "+projectId);
         button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 final String technolgyName = editText.getText().toString();

                 LiveData<Boolean> listLiveData = viewModelAddTechnology.insertTechnology(technolgyName);
                 listLiveData.observe(ActivityAddTechnology.this, new Observer<Boolean>() {
                     @Override
                     public void onChanged(@Nullable Boolean result) {

                         LiveData<Integer> getTechId = viewModelAddTechnology.getTechnologyId(technolgyName);
                         getTechId.observe(ActivityAddTechnology.this, new Observer<Integer>() {
                             @Override
                             public void onChanged(@Nullable Integer integer) {
                                 Integer id = new Integer(integer);
                                 int techId = id.intValue();

                                 LiveData<Boolean> insertId = viewModelAddTechnology.insertProjectAndTechnologyId(projectId,techId);
                                 insertId.observe(ActivityAddTechnology.this, new Observer<Boolean>() {
                                     @Override
                                     public void onChanged(@Nullable Boolean aBoolean) {
                                         setResult(Activity.RESULT_OK, null);
                                         finish();
                                     }
                                 });

                             }
                         });
                     }
                 });
             }
         });
    }
}
