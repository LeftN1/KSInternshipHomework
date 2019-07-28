package com.keepsolid.ksinternshiphomework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.keepsolid.ksinternshiphomework.fragments.MainFragment;
import com.keepsolid.ksinternshiphomework.fragments.ReceiverFragment;
import com.keepsolid.ksinternshiphomework.models.BookItem;
import com.keepsolid.ksinternshiphomework.models.SendItem;

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
        }

        sendItem = new SendItem() {
            @Override
            public void onSend(BookItem item) {
                if (inLandscapeMode) {
                    receiverFragment.setItem(item);
                    receiverFragment.showData();
                } else {
                    Intent intent = new Intent(Intent.ACTION_VIEW, item.getVolumeInfo().getPreviewLink());
                    startActivity(intent);
                }
            }
        };

        mainFragment.setSendItem(sendItem);

    }
}