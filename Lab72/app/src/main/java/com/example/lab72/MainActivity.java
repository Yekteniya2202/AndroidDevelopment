package com.example.lab72;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {


    static Music music;
    static SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DrawView(this));
        music = new Music(this, R.raw.music, false);
        DrawView.sounds = new GameSound(this);

        sharedPreferences = getSharedPreferences("MUSIC_SOUND", MODE_PRIVATE);
        boolean musicPlaying = sharedPreferences.getBoolean("music", false);
        boolean soundsPlaying = sharedPreferences.getBoolean("sounds", false);
        music.setPlaying(musicPlaying);
        DrawView.sounds.setEnabled(soundsPlaying);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        menu.findItem(R.id.music).setChecked(sharedPreferences.getBoolean("music", false));
        menu.findItem(R.id.sounds).setChecked(sharedPreferences.getBoolean("sounds", false));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        int id = menuItem.getItemId();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        switch (id){
            case R.id.restart:
                DrawView.board.restart();
                setContentView(new DrawView(this));
                break;
            case R.id.music:
                menuItem.setChecked(!menuItem.isChecked());
                music.setPlaying(menuItem.isChecked());
                editor.putBoolean("music", menuItem.isChecked());
                break;
            case R.id.sounds:
                menuItem.setChecked(!menuItem.isChecked());
                DrawView.sounds.setEnabled(menuItem.isChecked());
                editor.putBoolean("sounds", menuItem.isChecked());
                break;
        }
        editor.apply();
        return true;
    }

}