package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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

    Furniture furniture = new Furniture();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void calculateTotalCost(View view) {
        Furniture furniture = new Furniture();
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

            SQLiteDatabase db = getBaseContext().openOrCreateDatabase("furniture.db", MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS furniture (type TEXT, material TEXT, color TEXT, lacquer TEXT, warannty INTEGER, cost REAL)");
            String query = String.format("INSERT OR IGNORE INTO furniture VALUES ('%s', '%s', '%s', '%s', %d, %f)",
                    furniture.getFurnitureType().toString(),
                    furniture.getFurnitureColor().toString(),
                    furniture.getFurnitureMaterial().toString(),
                    furniture.isCoveredWithLacquer() ? "Да" : "Нет",
                    furniture.getWarranty(),
                    furniture.getTotalCost());
            db.execSQL(query);
            Intent intent = new Intent(this, OrderResults.class);
            intent.putExtra(Furniture.class.getSimpleName(), furniture);
            startActivity(intent);

        } catch (Exception e) {
            new AlertDialog.Builder(this)
                    .setTitle("Oшибка")
                    .setMessage(e.getMessage())
                    .setPositiveButton(android.R.string.ok, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }
}