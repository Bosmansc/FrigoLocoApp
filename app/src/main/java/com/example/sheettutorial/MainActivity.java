package com.example.sheettutorial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonAddItem,buttonListGames;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAddItem = (Button)findViewById(R.id.btn_addItem );
        buttonListGames = (Button)findViewById(R.id.btn_viewGames);
        buttonAddItem.setOnClickListener(this);
        buttonListGames.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v==buttonAddItem){

            Intent intent = new Intent(getApplicationContext(),AddItem.class);
            startActivity(intent);
        }

        if(v==buttonListGames){

            Intent intent = new Intent(getApplicationContext(),ListItem.class);
            startActivity(intent);
        }

    }
}