package com.example.lab72;

import java.util.Random;

public class ItemGame {
    public ValueGame valueGame;
    boolean visible = false;

    public ValueGame getValueGame() {
        return valueGame;
    }

    public void setValueGame(ValueGame valueGame) {
        this.valueGame = valueGame;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public ItemGame() {
        Random rnd = new Random();
        int random = rnd.nextInt(10);
        switch (random) {
            case 0:
                valueGame = ValueGame.Bomb;
                break;
            case 1:
                valueGame = ValueGame.Bonus;
                break;
            case 2:
            case 3:
            case 4:
                valueGame = ValueGame.Mushroom;
                break;
            default:
                valueGame = ValueGame.Nothing;
        }
    }

    public ValueGame select(){
        if (!visible) {
            visible = true;
            return  valueGame;
        }
        return null;
    }
}
