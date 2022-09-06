package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ConvertToBinary(View view) {
        EditText numberToConvertEditText = findViewById(R.id.numberToConvert);
        TextView numberConvertedTextView = findViewById(R.id.numberConverted);
        try {
            int numberToConvert = Integer.parseInt(numberToConvertEditText.getText().toString());
            String converted = Integer.toBinaryString(numberToConvert);
            numberConvertedTextView.setText(converted);
        }
        catch (Exception e){
            numberConvertedTextView.setText("Error while converting " + e.getMessage());
        }
    }
}