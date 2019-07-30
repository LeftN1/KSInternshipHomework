package com.keepsolid.ksinternshiphomework.adapters;

import android.net.Uri;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
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
    private OnBookRecyclerItemClickListener listener;
    Uri img = Uri.EMPTY;
    String sAuthors;


    public BookRecyclerAdapter(ArrayList<BookItem> items) {
        this.items = items;
    }

    public BookRecyclerAdapter(ArrayList<BookItem> items, OnBookRecyclerItemClickListener listener) {
        this.items = items;
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
        holder.title.setText(items.get(position).getVolumeInfo().getTitle());

        holder.description.setText(items.get(position).getVolumeInfo().getDescription());

        // Api Google Books, оказывается, не всегда выдает ссылки на изображения, создаются инстансы без imageLinks.
        // При попытке обратится .getVolumeInfo().getImageLinks().getThumbnail() возникает null pointer exception
        if(items.get(position).getVolumeInfo().getImageLinks() != null){
            img = items.get(position).getVolumeInfo().getImageLinks().getThumbnail();
        }
        else {
            img = Uri.EMPTY;
        }
        Glide.with(holder.thumbnail).load(img).placeholder(R.drawable.not_found).into(holder.thumbnail);
        //Picasso.with(holder.thumbnail.getContext()).load(img);//.placeholder(R.drawable.ic_account_multiple_grey600_24dp).into(holder.thumbnail);

        //Авторов тоже не всегда выдает((
        if(items.get(position).getVolumeInfo().getAuthors() != null){
            sAuthors = items.get(position).getVolumeInfo().getAuthorString();
        }
        else {
            sAuthors = "Unknown";
        }
        holder.authors.setText(sAuthors);
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

        TextView title;
        TextView authors;
        TextView description;
        AppCompatImageView thumbnail;

        public ViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.tv_book_title);
            authors = itemView.findViewById(R.id.tv_authors);
            description = itemView.findViewById(R.id.tv_description);
            thumbnail = itemView.findViewById(R.id.iv_thumbnail);
        }
    }

}
