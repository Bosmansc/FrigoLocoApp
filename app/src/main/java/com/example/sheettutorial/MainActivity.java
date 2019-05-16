package com.example.sheettutorial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonAddItem,buttonListGames,buttonNextGame, buttonAanwezigheid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        buttonAddItem = findViewById(R.id.btn_addItem );
        buttonListGames = findViewById(R.id.btn_viewGames);
        buttonNextGame = findViewById(R.id.btn_nextGame);
        buttonAanwezigheid = findViewById(R.id.btn_aanwezigheid);
        buttonAddItem.setOnClickListener(this);
        buttonListGames.setOnClickListener(this);
        buttonNextGame.setOnClickListener(this);
        buttonAanwezigheid.setOnClickListener(this);
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

        if(v==buttonAanwezigheid){

            Intent intent = new Intent(getApplicationContext(), AddPlayerToGame.class);
            startActivity(intent);
        }

    }
}