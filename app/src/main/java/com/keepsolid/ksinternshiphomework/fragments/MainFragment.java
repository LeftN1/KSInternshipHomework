package com.keepsolid.ksinternshiphomework.fragments;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.keepsolid.ksinternshiphomework.R;
import com.keepsolid.ksinternshiphomework.database.BookDBHelper;
import com.keepsolid.ksinternshiphomework.models.SendItem;
import com.keepsolid.ksinternshiphomework.adapters.BookRecyclerAdapter;
import com.keepsolid.ksinternshiphomework.api.ApiCallback;
import com.keepsolid.ksinternshiphomework.api.RestClient;
import com.keepsolid.ksinternshiphomework.listeners.OnBookRecyclerItemClickListener;
import com.keepsolid.ksinternshiphomework.models.BookErrorItem;
import com.keepsolid.ksinternshiphomework.models.BookItem;
import com.keepsolid.ksinternshiphomework.models.BookResponse;

import java.util.ArrayList;

import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    private final int MAX_RESULT = 40;

    private Toolbar toolbar;
    private RecyclerView recycler;
    private EditText titleInput;
    private AppCompatButton goButton;
    private ProgressBar progressBar;

    private ArrayList<BookItem> items;
    private SQLiteDatabase mDatabase;
    private BookRecyclerAdapter adapter;

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
        toolbar.setTitle("Google Books");

        items = new ArrayList<>();
        mDatabase = new BookDBHelper(v.getContext()).getWritableDatabase();

        recycler = v.findViewById(R.id.rv_recycler);
        titleInput = v.findViewById(R.id.et_title_input);
        goButton = v.findViewById(R.id.btn_go);
        progressBar = v.findViewById(R.id.pb_progress);

        adapter = new BookRecyclerAdapter(items, new OnBookRecyclerItemClickListener() {
            @Override
            public void onItemClick(View v, int position, Uri url) {
                sendItem.onSend(adapter.getItems().get(position));
            }
        });

        recycler.setLayoutManager(new LinearLayoutManager(v.getContext()));
        recycler.setAdapter(adapter);

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(titleInput.getText().toString())) {
                    titleInput.requestFocus();
                } else {
                    loadBooks(titleInput.getText().toString());
                }
            }
        });

        return v;
    }

    private void loadBooks(String title) {
        showProgressBlock();

        RestClient.getInstance().getService().getBooks(title, MAX_RESULT).enqueue(new ApiCallback<BookResponse>() {

            @Override
            public void success(Response<BookResponse> response) {
                items.clear();
                items.addAll(response.body().getBookItems());
                adapter.notifyDataSetChanged();
                hideProgressBlock();
            }

            @Override
            public void failure(BookErrorItem bookError) {
                if (TextUtils.isEmpty(bookError.getDocumentation_url())) {
                    makeErrorToast(bookError.getMessage());
                } else {
                    makeErrorToast(bookError.getMessage() + bookError.getDocumentation_url());
                }
                hideProgressBlock();
            }
        });
    }

    private void showProgressBlock() {
        if (progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }

    }

    private void hideProgressBlock() {
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
    }

    private void makeErrorToast(String errorMessage) {
        Toast.makeText(getView().getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void setSendItem(SendItem sendItem){
        this.sendItem = sendItem;
    }


}
