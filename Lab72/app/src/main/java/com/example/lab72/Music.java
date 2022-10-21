package com.example.lab72;

import android.content.Context;
import android.media.MediaPlayer;

public class Music {
    MediaPlayer mediaPlayer;

    public Music(Context context, int musicId, boolean startPlaying) {
        mediaPlayer = MediaPlayer.create(context, musicId);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
        if (!startPlaying) {
            mediaPlayer.pause();
        }
    }

    public void setPlaying(boolean play){
        if (play){
            mediaPlayer.start();
        }
        else{
            mediaPlayer.pause();
        }
    }
}
