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
import com.riham.sanaabilstore.R;
import com.riham.sanaabilstore.activities.DetailedActivity;
import com.riham.sanaabilstore.models.ShowAllModel;

import java.util.List;

public class ShowAllAdapter extends RecyclerView.Adapter<ShowAllAdapter.ViewHolder>{

    private Context context;
    private List<ShowAllModel> list;

    public ShowAllAdapter(Context context, List<ShowAllModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ShowAllAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.show_all_item,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ShowAllAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Glide.with(context).load(list.get(position).getImg_url()).into(holder.mItemImg);
        holder.mItemCost.setText("Rs"+list.get(position).getPrice());
        holder.mItemName.setText(list.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailedActivity.class);
                intent.putExtra("detailed",list.get(position));
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mItemName,mItemCost;
        ImageView mItemImg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mItemImg = itemView.findViewById(R.id.item_image);
            mItemName = itemView.findViewById(R.id.item_nam);
            mItemCost = itemView.findViewById(R.id.item_cost);
        }
    }
}
