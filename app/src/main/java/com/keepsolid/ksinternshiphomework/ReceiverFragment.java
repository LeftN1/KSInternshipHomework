package com.keepsolid.ksinternshiphomework;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReceiverFragment extends Fragment {

    private TextView showItemView;
    private Button exitButton;

    public ReceiverFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_receiver, container, false);
        showItemView = v.findViewById(R.id.show);
        exitButton = v.findViewById(R.id.exit);

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    getActivity().finish();
            }
        });

        return v;
    }

    public void showData(String details){
        showItemView.setText(details);
    }

    public void buttonOff(){
        exitButton.setVisibility(View.INVISIBLE);
    }
}
