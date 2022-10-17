package com.example.lab71;

import android.media.MediaPlayer;
import android.media.SoundPool;

public class Sound {

    static SoundPool soundPool;

    boolean enabled = false;

    int soundId;

    public static SoundPool getSoundPool() {
        return soundPool;
    }

    public static void setSoundPool(SoundPool soundPool) {
        Sound.soundPool = soundPool;
    }

    public int getSoundId() {
        return soundId;
    }

    public void setSoundId(int soundId) {
        this.soundId = soundId;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Sound(SoundPool soundPool) {
        this.soundPool = soundPool;
    }

    public void play(){
        if(enabled){
            soundPool.play(soundId, 1, 1, 0, 0, 1);
        }

    }
}
