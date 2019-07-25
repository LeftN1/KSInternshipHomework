package com.keepsolid.ksinternshiphomework;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.design.widget.FloatingActionButton;


import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    ArrayList <PurchaseItem> items;
    PurchaseAdapter adapter;
    FloatingActionButton btn;

    private SendItem sendItem;

    public MainFragment() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        toolbar = v.findViewById(R.id.toolbar);
        toolbar.setTitle("Список покупок");

        recyclerView = v.findViewById(R.id.rv_recycler);
        btn = v.findViewById(R.id.fab_add);

        items = new ArrayList<>();

        boolean checker;
        for (int i = 0; i < 100; i++){

            checker = (Math.random() > 0.4) ? false : true;

            items.add(new PurchaseItem(checker, "покупка " + i, Units.PCS, Math.random(), Math.random()*10, "Lorem ipsum ist dolore " + i ));
        }

        adapter = new PurchaseAdapter(items, v.getContext());

        adapter.setListener(new OnPurchaseItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                sendItem.onSend(adapter.getItems().get(position));
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        recyclerView.setAdapter(adapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void setSendItem(SendItem sendItem){
        this.sendItem = sendItem;
    }


}
