package com.example.sheettutorial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonAddItem,buttonListGames,buttonNextGame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        buttonAddItem = (Button)findViewById(R.id.btn_addItem );
        buttonListGames = (Button)findViewById(R.id.btn_viewGames);
        buttonNextGame = (Button)findViewById(R.id.btn_nextGame);
        buttonAddItem.setOnClickListener(this);
        buttonListGames.setOnClickListener(this);
        buttonNextGame.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v==buttonAddItem){

            Intent intent = new Intent(getApplicationContext(),AddItem.class);
            startActivity(intent);
        }

        if(v==buttonListGames){

            Intent intent = new Intent(getApplicationContext(), ListAllGames.class);
            startActivity(intent);
        }

        if(v==buttonNextGame){

            Intent intent = new Intent(getApplicationContext(),NextGame.class);
            startActivity(intent);
        }

    }
}