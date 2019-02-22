package com.example.multipletabledboperation.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.multipletabledboperation.R;
import com.example.multipletabledboperation.service.model.Products;
import com.example.multipletabledboperation.view.ui.ActivityProducts;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private Context context;
    private List<Products> productsList;

    public ProductAdapter(Context context, List<Products> productsList){
        this.productsList = productsList;
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
        final Products products = productsList.get(position);

        viewHolder.idTitle.setText("Id: "+products.getProdId());
        viewHolder.nameTitle.setText("Name: "+products.getProdName());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ActivityProducts)context).goToAddProject(products.getProdId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public void addItems(List<Products> productsList){
        this.productsList = productsList;
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

    public interface AddProject{
        void goToAddProject(int id);
    }
}
