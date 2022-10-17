package com.example.lab71;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    static MediaPlayer musicMediaPlayer;
    static Sound sound;
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
    }


    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem){
        int id = menuItem.getItemId();

        switch (id){
            case R.id.music:
                menuItem.setChecked(!menuItem.isChecked());
                if(!musicMediaPlayer.isPlaying())
                    musicMediaPlayer.start();
                else
                    musicMediaPlayer.pause();
                return true;
            case R.id.sounds:
                menuItem.setChecked(!menuItem.isChecked());
                if (!sound.isEnabled())
                    sound.setEnabled(true);
                else
                    sound.setEnabled(false);
                return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }
}