package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.lab2.furniture.Furniture;
import com.example.lab2.furniture.color.BlackColor;
import com.example.lab2.furniture.color.BrownColor;
import com.example.lab2.furniture.color.WhiteColor;
import com.example.lab2.furniture.material.Birch;
import com.example.lab2.furniture.material.Linden;
import com.example.lab2.furniture.material.Oak;
import com.example.lab2.furniture.type.Chair;
import com.example.lab2.furniture.type.Table;
import com.example.lab2.furniture.type.Wardrobe;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void calculateTotalCost(View view) {
        Furniture furniture = new Furniture();
        TextView textView = findViewById(R.id.logTextView);
        textView.setText("");
        try {
            RadioButton radioButton = findViewById(R.id.chairRadioButton);
            if (radioButton.isChecked()) furniture.setFurnitureType(new Chair());
            radioButton = findViewById(R.id.tableRadioButton);
            if (radioButton.isChecked()) furniture.setFurnitureType(new Table());
            radioButton = findViewById(R.id.wardrobeRadioButton);
            if (radioButton.isChecked()) furniture.setFurnitureType(new Wardrobe());

            radioButton = findViewById(R.id.oakRadioButton);
            if (radioButton.isChecked()) furniture.setFurnitureMaterial(new Oak());
            radioButton = findViewById(R.id.lindenRadioButton);
            if (radioButton.isChecked()) furniture.setFurnitureMaterial(new Linden());
            radioButton = findViewById(R.id.birchRadioButton);
            if (radioButton.isChecked()) furniture.setFurnitureMaterial(new Birch());

            radioButton = findViewById(R.id.whiteRadioButton);
            if (radioButton.isChecked()) furniture.setFurnitureColor(new WhiteColor());
            radioButton = findViewById(R.id.blackRadioButton);
            if (radioButton.isChecked()) furniture.setFurnitureColor(new BlackColor());
            radioButton = findViewById(R.id.brownRadioButton);
            if (radioButton.isChecked()) furniture.setFurnitureColor(new BrownColor());

            CheckBox checkBox = findViewById(R.id.lacquerCheckBox);
            if (checkBox.isChecked()) furniture.setCoveredWithLacquer(true);

            EditText editText = findViewById(R.id.warrantyЕditTextNumber);
            if (editText.getText().toString().equals("")) throw new Exception("Warranty is empty");
            furniture.setWarranty(Integer.parseInt(editText.getText().toString()));

            furniture.throwIfComponentsNull();

            Intent intent = new Intent(this, OrderResults.class);
            intent.putExtra(Furniture.class.getSimpleName(), furniture);
            startActivity(intent);

        } catch (Exception e) {
            textView = findViewById(R.id.logTextView);
            textView.setText(e.getMessage());
            textView.setTextColor(Color.RED);
        }
    }
}