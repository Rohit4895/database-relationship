package com.example.multipletabledboperation.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.multipletabledboperation.R;
import com.example.multipletabledboperation.service.model.Developer;
import com.example.multipletabledboperation.service.model.Projects;
import com.example.multipletabledboperation.view.ui.ActivityDeveloper;
import com.example.multipletabledboperation.view.ui.ActivityProjects;

import java.util.List;

public class DeveloperAdapter extends RecyclerView.Adapter<DeveloperAdapter.ViewHolder> {

    private Context context;
    private List<Developer> developersList;

    public DeveloperAdapter(Context context, List<Developer> developersList){
        this.context = context;
        this.developersList = developersList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(context).inflate(R.layout.items_to_show_developer,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        final Developer developer = developersList.get(position);

        viewHolder.idTitle.setText("ID: "+developer.getDevId());
        viewHolder.nameTitle.setText("Name: "+developer.getDevNAme());
        viewHolder.addTitle.setText("Address: "+developer.getDevAddress());
        viewHolder.mobTitle.setText("Mob.No: "+developer.getDevMobile());
    }

    @Override
    public int getItemCount() {
        return developersList.size();
    }

    public void addItems(List<Developer> developersList){
        this.developersList = developersList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView idTitle, nameTitle, addTitle, mobTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            idTitle = itemView.findViewById(R.id.idTitleDev);
            nameTitle = itemView.findViewById(R.id.nameTitleDev);
            addTitle = itemView.findViewById(R.id.addTitleDev);
            mobTitle = itemView.findViewById(R.id.mobTitleDev);
        }
    }

}
