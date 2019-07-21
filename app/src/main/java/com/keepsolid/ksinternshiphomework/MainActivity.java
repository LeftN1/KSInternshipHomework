package com.keepsolid.ksinternshiphomework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Objects;

public class MainActivity extends AppCompatActivity{

    private MainFragment mainFragment;
    private ReceiverFragment receiverFragment;
    public boolean inLandscapeMode;
    private SendHuman sendHuman;

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

        sendHuman = new SendHuman() {
            @Override
            public void onSend(Human human) {
                if (inLandscapeMode) {
                    receiverFragment.showData(human);
                } else {
                    Intent intent = new Intent(MainActivity.this, RecieverActivity.class);
                    intent.putExtra("first", human.getFirstName());
                    intent.putExtra("last", human.getLastName());
                    intent.putExtra("age", human.getAge());
                    startActivity(intent);
                }
            }
        };

        mainFragment.setSendHuman(sendHuman);

    }
}