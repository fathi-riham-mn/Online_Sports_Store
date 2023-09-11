package com.riham.sanaabilstore.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.riham.sanaabilstore.activities.DetailedActivity;
import com.riham.sanaabilstore.R;
import com.riham.sanaabilstore.models.PopularProductsModel;

import java.util.List;

public class PopularProductsAdapter extends RecyclerView.Adapter<PopularProductsAdapter.ViewHolder>{

    private Context context;
    private List<PopularProductsModel> popularProductsModelList;

    public PopularProductsAdapter(Context context, List<PopularProductsModel> popularProductsModelList) {
        this.context = context;
        this.popularProductsModelList = popularProductsModelList;
    }

    @NonNull
    @Override
    public PopularProductsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_items,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PopularProductsAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Glide.with(context).load(popularProductsModelList.get(position).getImg_url()).into(holder.popImg);
        holder.popName.setText(popularProductsModelList.get(position).getName());
        holder.popPrice.setText(String.valueOf(popularProductsModelList.get(position).getPrice()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailedActivity.class);
                intent.putExtra("detailed",popularProductsModelList.get(position));
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return popularProductsModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView popName,popPrice;
        ImageView popImg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            popImg = itemView.findViewById(R.id.all_img);
            popName = itemView.findViewById(R.id.all_product_name);
            popPrice = itemView.findViewById(R.id.all_price);
        }
    }
}
