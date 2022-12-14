package com.example.lab71;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    static MediaPlayer musicMediaPlayer;
    static Sound sound;

    static SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DrawView(this));

        musicMediaPlayer = MediaPlayer.create(this, R.raw.music);
        SoundPool soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        int soundId = soundPool.load(this, R.raw.sound, 1);
        sound = new Sound(soundPool);
        sound.setSoundId(soundId);
        musicMediaPlayer.setLooping(true);

        sharedPreferences = getSharedPreferences("MUSIC_SOUND", MODE_PRIVATE);
        if (sharedPreferences.getBoolean("music", false)){
            musicMediaPlayer.start();
        }
        else{
            musicMediaPlayer.pause();
        }

        sound.setEnabled(sharedPreferences.getBoolean("sound", false));


    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem){
        int id = menuItem.getItemId();
        SharedPreferences.Editor editor = sharedPreferences.edit();

        switch (id){
            case R.id.music:
                menuItem.setChecked(!menuItem.isChecked());
                if(!musicMediaPlayer.isPlaying()) {
                    musicMediaPlayer.start();
                    editor.putBoolean("music", true);
                }
                else {
                    musicMediaPlayer.pause();
                    editor.putBoolean("music", false);
                }
                return true;
            case R.id.sounds:
                menuItem.setChecked(!menuItem.isChecked());
                if (!sound.isEnabled()) {
                    editor.putBoolean("sounds", true);
                    sound.setEnabled(true);
                }
                else {
                    editor.putBoolean("sounds", false);
                    sound.setEnabled(false);
                }
                return true;
            case R.id.restart:
                DrawView.game.restart();
                setContentView(new DrawView(this));
        }
        return super.onOptionsItemSelected(menuItem);
    }
}