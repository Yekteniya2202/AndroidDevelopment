package com.example.lab72;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

public class DrawView extends View {
    Paint paint = new Paint();
    static Board board = new Board();

    static GameSound sounds;

    int wPerItem;

    public DrawView(Context context) {
        super(context);
    }


    public void DrawItem(Canvas canvas, int x, int y, int itemId) {
        Bitmap bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), itemId), wPerItem, wPerItem, true);
        canvas.drawBitmap(bitmap, x, y, paint);
    }

    public void DrawBackground(Canvas canvas, int w, int h, int itemId) {
        Bitmap bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), itemId), w, h, true);
        canvas.drawBitmap(bitmap, 0, 0, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int w = displayMetrics.widthPixels;
        int h = displayMetrics.heightPixels;

        int i = (int) y / wPerItem;
        int j = (int) x / wPerItem;

        if (i >= board.getBoardItems().length || j >= board.getBoardItems().length) {
            return true;
        }
        ValueGame valueGame = board.select(i, j);
        if (valueGame != null && sounds != null) {
            sounds.play(valueGame);
        }
        invalidate();
        System.out.println(i + " " + j);
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {


        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int w = displayMetrics.widthPixels;
        int h = displayMetrics.heightPixels;

        //square items
        wPerItem = w / board.getBoardItems().length;

        System.out.println(board);

        DrawBackground(canvas, w, w, R.drawable.background);
        for (int row = 0; row < board.getBoardItems().length; row++) {
            for (int col = 0; col < board.getBoardItems()[row].length; col++) {
                ItemGame itemGame = board.getBoardItems()[col][row];
                int itemId = 0;
                if (!itemGame.isVisible()) {
                    itemId = R.drawable.bush;
                } else {
                    switch (board.getBoardItems()[col][row].valueGame) {
                        case Mushroom:
                            itemId = R.drawable.mushroom;
                            break;
                        case Bomb:
                            itemId = R.drawable.bomb;
                            break;
                        case Bonus:
                            itemId = R.drawable.bonus;
                            break;
                        case Nothing:
                            break;
                        default:
                            throw new RuntimeException("Something went wrong");
                    }
                }
                if (itemId != 0) {
                    DrawItem(canvas, row * wPerItem, col * wPerItem, itemId);
                }

            }
            paint.setTextSize(50);
            paint.setColor(Color.BLACK);
            canvas.drawText("Attempts: " + board.getAttempts(), 10, w + 50, paint);
            canvas.drawText("Score: " + board.getScore(), 10, w + 100, paint);
            if (!board.isRunning()) {
                paint.setColor(Color.RED);
                canvas.drawText("Game over", 10, w + 150, paint);
            }
        }
    }
}
