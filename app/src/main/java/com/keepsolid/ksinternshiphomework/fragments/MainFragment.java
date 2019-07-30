package com.keepsolid.ksinternshiphomework.fragments;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
import com.keepsolid.ksinternshiphomework.database.BookDBSchema;
import com.keepsolid.ksinternshiphomework.database.BookDBSchema.BookTable;
import com.keepsolid.ksinternshiphomework.database.MyCursor;
import com.keepsolid.ksinternshiphomework.models.SendItem;
import com.keepsolid.ksinternshiphomework.adapters.BookRecyclerAdapter;
import com.keepsolid.ksinternshiphomework.api.ApiCallback;
import com.keepsolid.ksinternshiphomework.api.RestClient;
import com.keepsolid.ksinternshiphomework.listeners.OnBookRecyclerItemClickListener;
import com.keepsolid.ksinternshiphomework.models.BookErrorItem;
import com.keepsolid.ksinternshiphomework.models.BookItem;
import com.keepsolid.ksinternshiphomework.models.BookResponse;

import java.util.ArrayList;
import java.util.List;

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



        mDatabase = new BookDBHelper(v.getContext()).getWritableDatabase();

        recycler = v.findViewById(R.id.rv_recycler);
        titleInput = v.findViewById(R.id.et_title_input);
        goButton = v.findViewById(R.id.btn_go);
        progressBar = v.findViewById(R.id.pb_progress);
        recycler.setLayoutManager(new LinearLayoutManager(v.getContext()));

//        items = new ArrayList<>();
        items = getBooksFromDB();

        if (adapter == null) {
            adapter = new BookRecyclerAdapter(items, new OnBookRecyclerItemClickListener() {
                @Override
                public void onItemClick(View v, int position, Uri url) {
                    sendItem.onSend(adapter.getItems().get(position));
                }
            });
            recycler.setAdapter(adapter);
        }
        else {
            adapter.setItems(items);
            adapter.notifyDataSetChanged();
        }




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

    private static ContentValues getContentValues(BookItem bookItem){
        ContentValues values = new ContentValues();
        values.put(BookTable.cols.ID, bookItem.getId());
        values.put(BookTable.cols.TITLE, bookItem.getVolumeInfo().getTitle());
        values.put(BookTable.cols.AUTHORS, bookItem.getVolumeInfo().getAuthorString());
        values.put(BookTable.cols.DESCRIPTION, bookItem.getVolumeInfo().getDescription());
        values.put(BookTable.cols.URL, bookItem.getVolumeInfo().getPreviewLink().toString());
        values.put(BookTable.cols.THUMBNAIL, bookItem.getVolumeInfo().getImageLinks().getThumbnail().toString());
        return values;
    }

    private void saveItems(){
        mDatabase.delete(BookTable.NAME,null,null);
        for(BookItem bookItem : items){
            mDatabase.insert(BookTable.NAME, null, getContentValues(bookItem));
        }
    }

    private void updateSavedBook(BookItem bookItem){
        String id = bookItem.getId();
        ContentValues values = getContentValues(bookItem);

        mDatabase.update(BookTable.NAME, values, BookTable.cols.ID + " = ?", new String[]{id});
    }

    private MyCursor queryBooks(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                BookTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new MyCursor(cursor);
    }

    public ArrayList<BookItem> getBooksFromDB(){
        ArrayList<BookItem> bookItems = new ArrayList<>();
        MyCursor cursor = queryBooks(null, null);

        try{
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                bookItems.add(cursor.getBook());
                cursor.moveToNext();
            }
        }finally{
            cursor.close();
        }
            return bookItems;
    }

    private void loadBooks(String title) {
        showProgressBlock();

        RestClient.getInstance().getService().getBooks(title, MAX_RESULT).enqueue(new ApiCallback<BookResponse>() {

            @Override
            public void success(Response<BookResponse> response) {
                items.clear();
                items.addAll(response.body().getBookItems());
                adapter.notifyDataSetChanged();
                saveItems();
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
