package com.keepsolid.ksinternshiphomework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.keepsolid.ksinternshiphomework.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onSend(View view){

        String first = ((EditText) findViewById(R.id.first)).getText().toString();
        String last = ((EditText) findViewById(R.id.last)).getText().toString();

        TextView ageTxtView = (EditText) findViewById(R.id.age);
        int age;

        TextView txt = (TextView) findViewById(R.id.errormsg);

//      По нажатию на кнопку текстовые поля проверяется на пустоту
        if(!first.equals("") && !last.equals("") && ageTxtView.length() > 0) {

            age = Integer.parseInt(ageTxtView.getText().toString());
            //Ecли поля НЕ пустые - берем из них данные и собираем класс "Human" у которого соответственно есть поля "Имя", "Фамилия" и "Возраст" (int).
            Human human = new Human(first, last, age);

            //Этот объект затем передаем через интент на второе активити
            Intent intent = new Intent(this, RecieverActivity.class);
            intent.putExtra("first", human.getFirstName());
            intent.putExtra("last", human.getLastName());
            intent.putExtra("age", human.getAge());
            startActivity(intent);
        }
        else {
            txt.setText("All fields must be non-empty.");
        }
    }
}