package com.keepsolid.ksinternshiphomework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;



public class RecieverActivity extends AppCompatActivity{

    private PurchaseItem item;
    private ReceiverFragment receiverFragment;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        intent = getIntent();
        String details = intent.getStringExtra("details");


        setContentView(R.layout.activity_reciever);

        receiverFragment = (ReceiverFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_receiver);
        receiverFragment.showData(details);

    }

}
