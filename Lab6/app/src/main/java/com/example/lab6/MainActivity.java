package com.example.lab6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private static boolean isLightOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void switchLightning(View view) {
        isLightOn = !isLightOn;
        ImageView imageView = findViewById(R.id.imageView);
        if (isLightOn) {
            imageView.setImageResource(R.drawable.light_on);
        } else {
            imageView.setImageResource(R.drawable.light_off);
        }
        flashws(isLightOn);
    }

    public void flashws(boolean sw) {
        try {
            CameraManager camManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
            String cameraId = null;
            cameraId = camManager.getCameraIdList()[0];
            if (sw) {
                camManager.setTorchMode(cameraId, true);   //Turn ON

            } else {
                camManager.setTorchMode(cameraId, false);
            }
        } catch (RuntimeException | CameraAccessException e) {
            System.out.println(e);
        }
    }

    public void vibrateMorse(View view) throws InterruptedException {
        TextView textView = findViewById(R.id.textViewMorse);
        String morse = textView.getText().toString();
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator.hasVibrator()) {
            for (char c : morse.toCharArray()) {
                if (c == '.') {
                    vibrator.vibrate(200);
                    Thread.sleep(300);
                }
                else {
                    vibrator.vibrate(500);
                    Thread.sleep(600);
                }
            }
        }
    }

    public void convertToMorse(View view) {
        EditText editText = findViewById(R.id.editTextMessage);
        String text = editText.getText().toString();
        String morse = englishToMorse(code, text, letter);
        TextView textView = findViewById(R.id.textViewMorse);
        textView.setText(morse);
    }


    private static String[] code
            = {".-", "-...", "-.-.", "-..", ".",
            "..-.", "--.", "....", "..", ".---",
            "-.-", ".-..", "--", "-.", "---",
            ".--.", "--.-", ".-.", "...", "-",
            "..-", "...-", ".--", "-..-", "-.--",
            "--..", "|"};

    private static char[] letter
            = {'a', 'b', 'c', 'd', 'e', 'f',
            'g', 'h', 'i', 'j', 'k', 'l',
            'm', 'n', 'o', 'p', 'q', 'r',
            's', 't', 'u', 'v', 'w', 'x',
            'y', 'z', '1', '2', '3', '4',
            '5', '6', '7', '8', '9', '0'};

    public static String englishToMorse(String[] code,
                                        String englishLang,
                                        char[] letter) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < englishLang.length(); i++) {
            for (int j = 0; j < letter.length; j++) {
                if (englishLang.charAt(i) == letter[j]) {
                    result.append(code[j]);
                    break;
                }
            }
        }
        return result.toString();
    }

}