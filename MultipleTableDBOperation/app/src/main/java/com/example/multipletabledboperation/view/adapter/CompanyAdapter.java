package com.example.multipletabledboperation.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.multipletabledboperation.R;
import com.example.multipletabledboperation.service.model.Company;
import com.example.multipletabledboperation.view.ui.ActivityCompany;

import java.util.List;

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.ViewHolder> {
    private Context context;
    private List<Company> list;

    public CompanyAdapter(Context context, List<Company> list){
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(context).inflate(R.layout.items_to_show, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        final Company company = list.get(position);

        viewHolder.idTitle.setText("ID: "+company.getCompId());
        viewHolder.nameTitle.setText("Name: "+company.getCompName());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ActivityCompany)context).goToAddProd(company.getCompId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItems(List<Company> list){
        this.list = list;
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

    public interface AddProd{
        void goToAddProd(int id);
    }
}
