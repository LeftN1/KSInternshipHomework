package com.keepsolid.ksinternshiphomework.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.keepsolid.ksinternshiphomework.R;
import com.keepsolid.ksinternshiphomework.models.BookItem;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReceiverFragment extends Fragment {

    private TextView title;
    private TextView authors;
    private TextView description;
    private Button exitButton;
    private BookItem item;

    public ReceiverFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_receiver, container, false);
        title = v.findViewById(R.id.title);
        authors = v.findViewById(R.id.authors);
        description = v.findViewById(R.id.description);
        exitButton = v.findViewById(R.id.exit);



        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(item != null) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, item.getVolumeInfo().getPreviewLink());
                    startActivity(intent);
                }
            }
        });

        return v;
    }

    public void showData(){
        if(item != null){
            title.setText("title: " + item.getVolumeInfo().getTitle());

            String authorsString = "author(s): " + item.getVolumeInfo().getAuthorString();

            authors.setText(authorsString);
            description.setText(item.getVolumeInfo().getDescription());
        }
    }

    public void setItem(BookItem item){
        this.item = item;
    }

}
