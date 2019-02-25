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
import android.widget.Toast;

import com.example.multipletabledboperation.R;
import com.example.multipletabledboperation.viewModel.ViewModelAddProject;

public class ActivityAddProject extends AppCompatActivity {
    private TextView textView;
    private EditText editText;
    private Button button;
    private ViewModelAddProject viewModelAddProject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);

        viewModelAddProject = ViewModelProviders.of(this).get(ViewModelAddProject.class);
        final int productId = getIntent().getIntExtra("productId",0);

        textView = findViewById(R.id.productId);
        editText = findViewById(R.id.projEditText);
        button = findViewById(R.id.projSaveButton);

        textView.setText("Product Id: "+productId);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String projectName = editText.getText().toString();

                LiveData<Boolean> insertProject = viewModelAddProject.insertProject(projectName);
                insertProject.observe(ActivityAddProject.this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(@Nullable Boolean result) {
                        if (!result){
                            Toast.makeText(ActivityAddProject.this,
                                    "Error while insertion",
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }

                        LiveData<Integer> getProjectId = viewModelAddProject.getProjectId(projectName);
                        getProjectId.observe(ActivityAddProject.this, new Observer<Integer>() {
                            @Override
                            public void onChanged(@Nullable Integer integer) {
                                Integer id = new Integer(integer);
                                int projectId = id.intValue();

                                LiveData<Boolean> insertId = viewModelAddProject.insertProductAndProjectId(productId,projectId);
                                insertId.observe(ActivityAddProject.this, new Observer<Boolean>() {
                                    @Override
                                    public void onChanged(@Nullable Boolean result1) {
                                        if (!result1){
                                            Toast.makeText(ActivityAddProject.this,
                                                    "Error while insertion",
                                                    Toast.LENGTH_SHORT).show();
                                            return;
                                        }
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
