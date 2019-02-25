package com.example.multipletabledboperation.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.multipletabledboperation.R;
import com.example.multipletabledboperation.service.model.Projects;
import com.example.multipletabledboperation.view.ui.ActivityProjects;

import java.util.List;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ViewHolder> {
    private Context context;
    private List<Projects> projectsList;

    public ProjectAdapter(Context context, List<Projects> projectsList){
        this.context = context;
        this.projectsList = projectsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(context).inflate(R.layout.items_to_show,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        final Projects projects = projectsList.get(position);

        viewHolder.idTitle.setText("Project ID: "+projects.getProId());
        viewHolder.nameTitle.setText("Project Name: "+projects.getProName());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ActivityProjects)context).goToAddTechnology(projects.getProId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return projectsList.size();
    }

    public void addItems(List<Projects> projectsList){
        this.projectsList = projectsList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView idTitle, nameTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            idTitle = itemView.findViewById(R.id.idTitle);
            nameTitle = itemView.findViewById(R.id.nameTitle);
        }
    }

    public interface AddTechnology{
        void goToAddTechnology(int projectId);
    }
}
