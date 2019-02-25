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
import com.example.multipletabledboperation.viewModel.ViewModelAddDeveloper;

public class ActivityAddDeveloper extends AppCompatActivity {
    private TextView textView;
    private EditText name, add, mobile;
    private Button button;
    private ViewModelAddDeveloper viewModelAddDeveloper;
    private int techId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_developer);

        viewModelAddDeveloper = ViewModelProviders.of(ActivityAddDeveloper.this).get(ViewModelAddDeveloper.class);
        techId = getIntent().getIntExtra("technologyId",0);
        name = findViewById(R.id.devEditText);
        add = findViewById(R.id.devEditTextAdd);
        mobile = findViewById(R.id.devEditTextMob);
        button = findViewById(R.id.devSaveButton);

        techId = getIntent().getIntExtra("technologyId",0);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String devName = name.getText().toString();
                String devAdd = add.getText().toString();
                int devMobile = Integer.valueOf(mobile.getText().toString());
                LiveData<Boolean> insertDev = viewModelAddDeveloper.insertDeveloper(devName,devAdd,devMobile);
                insertDev.observe(ActivityAddDeveloper.this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(@Nullable Boolean aBoolean) {
                        LiveData<Integer> getDevId = viewModelAddDeveloper.getDveloperId(devName);
                        getDevId.observe(ActivityAddDeveloper.this, new Observer<Integer>() {
                            @Override
                            public void onChanged(@Nullable Integer integer) {
                                Integer id = new Integer(integer);
                                int devId = id.intValue();
                                LiveData<Boolean> insertId = viewModelAddDeveloper.insertTechnologyAndDeveloperId(techId,devId);
                                insertId.observe(ActivityAddDeveloper.this, new Observer<Boolean>() {
                                    @Override
                                    public void onChanged(@Nullable Boolean aBoolean) {
                                        setResult(Activity.RESULT_OK,null);
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
