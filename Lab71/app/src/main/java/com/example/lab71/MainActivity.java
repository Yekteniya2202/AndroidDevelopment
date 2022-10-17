package com.example.lab71;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private static final String PLAYERS = "Players";

    static SharedPreferences settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        settings = getSharedPreferences(PLAYERS, MODE_PRIVATE);
    }


    public void saveNames(View view) {
        // получаем введенное имя
        EditText name1Box = findViewById(R.id.editTextTextPersonName1);
        EditText name2Box = findViewById(R.id.editTextTextPersonName2);
        String name1 = name1Box.getText().toString();
        String name2 = name2Box.getText().toString();
        // сохраняем его в настройках
        SharedPreferences.Editor prefEditor = settings.edit();
        if (!name1.isEmpty()) prefEditor.putString("player1", name1);
        if (!name2.isEmpty()) prefEditor.putString("player2", name2);
        prefEditor.apply();


        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

}