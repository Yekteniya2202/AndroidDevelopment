package com.example.lab72;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class GameSound {
    SoundPool soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);

    boolean enabled = false;

    int bombSoundId, mushroomSoundId, bonusSoundId;
    public GameSound(Context context) {
        bombSoundId = soundPool.load(context, R.raw.bomb, 1);
        mushroomSoundId = soundPool.load(context, R.raw.mushroom, 2);
        bonusSoundId = soundPool.load(context, R.raw.bonus, 3);
    }

    public void play(ValueGame valueGame){
        if(enabled){
            switch (valueGame){
                case Bomb:
                    soundPool.play(bombSoundId, 1, 1, 0, 0, 1);
                    break;
                case Bonus:
                    soundPool.play(bonusSoundId, 1, 1, 0, 0, 1);
                    break;
                case Mushroom:
                    soundPool.play(mushroomSoundId, 1, 1, 0, 0, 1);
                    break;
            }

        }
    }

    public SoundPool getSoundPool() {
        return soundPool;
    }

    public void setSoundPool(SoundPool soundPool) {
        this.soundPool = soundPool;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

}
