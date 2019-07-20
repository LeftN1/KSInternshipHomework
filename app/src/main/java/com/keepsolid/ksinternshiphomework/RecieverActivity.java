package com.keepsolid.ksinternshiphomework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;



public class RecieverActivity extends AppCompatActivity{

    private Human human;
    private ReceiverFragment receiverFragment;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        intent = getIntent();
        String first = intent.getStringExtra("first");
        String last = intent.getStringExtra("last");
        int age = intent.getIntExtra("age",0);
        human = new Human(first, last, age);

        setContentView(R.layout.activity_reciever);

        receiverFragment = (ReceiverFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_receiver);
        receiverFragment.showData(human);

    }

}
