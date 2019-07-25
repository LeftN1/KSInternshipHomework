package com.keepsolid.ksinternshiphomework;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;

import java.util.ArrayList;

public class PurchaseAdapter extends RecyclerView.Adapter<PurchaseViewHolder>{

    private ArrayList<PurchaseItem> items;
    private OnPurchaseItemClickListener listener;
    private Context context;

    public PurchaseAdapter(ArrayList<PurchaseItem> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public PurchaseViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_list_item, parent, false);
        final PurchaseViewHolder viewHolder = new PurchaseViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null){
                    listener.onItemClick(view, viewHolder.getAdapterPosition());
                }
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PurchaseViewHolder holder, int position) {

        holder.isDone.setChecked(items.get(position).isDone());
        holder.name.setText(items.get(position).getName());
        holder.measurement.setText(items.get(position).getMeasurement().toString());


        holder.qtty.setText(String.format("%.2f",items.get(position).getQtty()));
        holder.price.setText(String.format("%.2f",items.get(position).getPrice()));
        holder.total.setText(String.format("%.2f",items.get(position).getTotal()));

        if (position == items.size() - 1) {
            holder.divider.setVisibility(View.INVISIBLE);
        } else {
            holder.divider.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public OnPurchaseItemClickListener getListener() {
        return listener;
    }

    public void setListener(OnPurchaseItemClickListener listener) {
        this.listener = listener;
    }

    public ArrayList<PurchaseItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<PurchaseItem> items) {
        this.items = items;
    }
}

