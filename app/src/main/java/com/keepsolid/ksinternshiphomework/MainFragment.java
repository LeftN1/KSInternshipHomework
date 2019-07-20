package com.keepsolid.ksinternshiphomework;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    private EditText firstTxt;
    private EditText lastTxt;
    private EditText ageTxt;
    private Button sendButton;
    private TextView errMsgView;

    private Human human;
    private SendHuman sendHuman;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        firstTxt = v.findViewById(R.id.first);
        lastTxt = v.findViewById(R.id.last);
        ageTxt = v.findViewById(R.id.age);

        sendButton = v.findViewById(R.id.send_bttn);
        errMsgView = v.findViewById(R.id.errormsg);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String first = firstTxt.getText().toString();
                String last = lastTxt.getText().toString();
                boolean isCorrect = !first.isEmpty() && !last.isEmpty() && !ageTxt.getText().toString().isEmpty();

                if(isCorrect){
                    errMsgView.setText("");
                    int age = Integer.parseInt(ageTxt.getText().toString());
                    errMsgView.setText("");
                    int a = 2;
                    human = new Human(first, last, age);
                    sendHuman.onSend(human);
                } else {
                    errMsgView.setText("All fields must be non-empty.");
                }
            }
        });

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        sendHuman = (SendHuman) context;
    }

    @Override
    public void onResume() {
        super.onResume();
        firstTxt.setText("");
        lastTxt.setText("");
        ageTxt.setText("");
    }
}
