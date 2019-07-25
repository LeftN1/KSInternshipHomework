package com.keepsolid.ksinternshiphomework;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class PurchaseViewHolder extends RecyclerView.ViewHolder {

    CheckBox isDone;
    TextView name;
    TextView measurement;
    TextView qtty;
    TextView price;
    TextView total;
    View divider;

    public PurchaseViewHolder(View itemView) {
        super(itemView);

        isDone = itemView.findViewById(R.id.purchase_check);
        name = itemView.findViewById(R.id.purchase_title);
        measurement = itemView.findViewById(R.id.measurement);
        qtty = itemView.findViewById(R.id.quantity);
        price = itemView.findViewById(R.id.price);
        total = itemView.findViewById(R.id.total);
        divider = itemView.findViewById(R.id.divider);
    }
}
