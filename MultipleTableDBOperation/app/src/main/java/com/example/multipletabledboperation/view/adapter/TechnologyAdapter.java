package com.example.multipletabledboperation.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.multipletabledboperation.R;
import com.example.multipletabledboperation.service.model.Technology;
import com.example.multipletabledboperation.view.ui.ActivityTechnology;

import java.util.List;

public class TechnologyAdapter extends RecyclerView.Adapter<TechnologyAdapter.ViewHolder> {
    private Context context;
    private List<Technology> technologyList;

    public TechnologyAdapter(Context context, List<Technology> technologyList){
        this.context = context;
        this.technologyList = technologyList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(context).inflate(R.layout.items_to_show,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        final Technology technology = technologyList.get(position);

        viewHolder.idTitle.setText("ID: "+technology.getTechId());
        viewHolder.nameTitle.setText("Name: "+technology.getTechName());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ActivityTechnology)context).goToAddDeveloper(technology.getTechId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return technologyList.size();
    }

    public void addItems(List<Technology> technologyList){
        this.technologyList = technologyList;
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

    public interface AddDeveloper{
        void goToAddDeveloper(int technologyId);
    }
}
