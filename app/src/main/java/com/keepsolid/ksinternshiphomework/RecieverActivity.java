package com.keepsolid.ksinternshiphomework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.keepsolid.ksinternshiphomework.R;

public class RecieverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Этот объект затем передаем через интент на второе активити, где выводим его в текстовом поле.
        Intent intent = getIntent();

//      Передавать можно:
//      1) отдельными полями extra с разными ключами (отдельно имя, отдельно фамилию, отдельно возраст) и потом собирать объект снова на новом активити
        String first = intent.getStringExtra("first");
        String last = intent.getStringExtra("last");
        int age = intent.getIntExtra("age",0);

        Human human = new Human(first, last, age);

        setContentView(R.layout.activity_reciever);

        TextView txt = (TextView) findViewById(R.id.show);

        txt.setText(human.toString());
    }

    public void onExitClick(View view){
        //Кнопка на втором активити - "Закрыть", которая закрывает это активити (вызов finish().
        finish();
    }
}
