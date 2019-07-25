package com.keepsolid.ksinternshiphomework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity{

    private MainFragment mainFragment;
    private ReceiverFragment receiverFragment;
    public boolean inLandscapeMode;
    private SendItem sendItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inLandscapeMode = findViewById(R.id.fragment_receiver) != null;
        mainFragment = (MainFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_main);
        if(inLandscapeMode){
            receiverFragment = (ReceiverFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_receiver);
            receiverFragment.buttonOff();
        }

        sendItem = new SendItem() {
            @Override
            public void onSend(PurchaseItem item) {
                if (inLandscapeMode) {
                    receiverFragment.showData(item.getDetails());
                } else {
                    Intent intent = new Intent(MainActivity.this, RecieverActivity.class);
                    intent.putExtra("details", item.getDetails());
                    startActivity(intent);
                }
            }
        };

        mainFragment.setSendItem(sendItem);

    }
}