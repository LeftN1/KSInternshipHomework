package com.keepsolid.ksinternshiphomework.adapters;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.keepsolid.ksinternshiphomework.R;
import com.keepsolid.ksinternshiphomework.listeners.OnBookRecyclerItemClickListener;
import com.keepsolid.ksinternshiphomework.models.BookItem;

import java.util.ArrayList;

public class BookRecyclerAdapter extends RecyclerView.Adapter<BookRecyclerAdapter.ViewHolder> {

    private ArrayList<BookItem> items;
    private Context ctx;
    private OnBookRecyclerItemClickListener listener;

    public BookRecyclerAdapter(ArrayList<BookItem> items, Context ctx) {
        this.items = items;
        this.ctx = ctx;
    }

    public BookRecyclerAdapter(ArrayList<BookItem> items, Context ctx, OnBookRecyclerItemClickListener listener) {
        this.items = items;
        this.ctx = ctx;
        this.listener = listener;
    }

    @Override
    public BookRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_list_item, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(view, viewHolder.getAdapterPosition(), items.get(viewHolder.getAdapterPosition()).getVolumeInfo().getPreviewLink());
                }
            }
        });


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BookRecyclerAdapter.ViewHolder holder, int position) {
        holder.description.setText(items.get(position).getVolumeInfo().getDescription());
        holder.name.setText(items.get(position).getVolumeInfo().getTitle());
        Glide.with(holder.avatar).load(items.get(position).getVolumeInfo().getImageLinks().getThumbnail()).placeholder(R.drawable.ic_account_multiple_grey600_24dp).into(holder.avatar);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public ArrayList<BookItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<BookItem> items) {
        this.items = items;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView description;
        AppCompatImageView avatar;

        public ViewHolder(View itemView) {
            super(itemView);

            Log.e("TaskRecyclerAdapter", "finding views!");

            description = itemView.findViewById(R.id.tv_repo_desc);
            name = itemView.findViewById(R.id.tv_repo_name);
            avatar = itemView.findViewById(R.id.iv_user_avatar);

        }
    }

}
