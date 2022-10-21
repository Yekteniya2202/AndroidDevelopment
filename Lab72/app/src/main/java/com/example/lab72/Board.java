package com.example.lab72;

public class Board {
    ItemGame[][] boardItems = new ItemGame[10][10];

    int attempts;
    int score;
    boolean running;

    public Board() {
        setUpBoard();
    }

    void setUpBoard() {
        attempts = 10;
        score = 0;
        running = true;
        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 10; column++) {
                boardItems[row][column] = new ItemGame();
            }
        }
    }

    void restart(){
        setUpBoard();
    }

    ValueGame select(int row, int col) {
        if (attempts <= 0) {
            attempts = 0;
            running = false;
        }
        if (attempts > 0) {
            ValueGame selected = boardItems[row][col].select();
            if (selected != null) {
                switch (selected) {
                    case Bomb:
                        attempts -= 2;
                        break;
                    case Bonus:
                        attempts += 2;
                        break;
                    case Mushroom:
                        score += 5;
                        attempts--;
                        break;
                    case Nothing:
                        attempts--;
                        break;
                    default:
                        throw new RuntimeException("Something went wrong");
                }
                return selected;
            }
        }
        return null;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        for (int row = 0; row < 10; row++) {
            sb.append("[");
            for (int column = 0; column < 10; column++) {
                sb.append(boardItems[row][column].valueGame + ", ");
            }
            sb.append("],");
            sb.append("\n");
        }
        return sb.toString();
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public ItemGame[][] getBoardItems() {
        return boardItems;
    }

    public void setBoardItems(ItemGame[][] boardItems) {
        this.boardItems = boardItems;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
