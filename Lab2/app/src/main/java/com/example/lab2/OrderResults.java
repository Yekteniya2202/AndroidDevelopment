package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.lab2.furniture.Furniture;

public class OrderResults extends AppCompatActivity {

    Furniture furniture = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_results);

        Bundle args = getIntent().getExtras();
        if (args != null){
            furniture = (Furniture) args.getSerializable(Furniture.class.getSimpleName());
        }
        printFurniture(furniture);
    }

    private void printFurniture(Furniture furniture) {
        TextView textView = findViewById(R.id.totalTextView);
        try {
            String s = "Состав заказа:\n";
            s += "Тип мебели: " + furniture.getFurnitureType().toString() + '\n';
            s += "Материал: " + furniture.getFurnitureMaterial().toString() + '\n';
            s += "Цвет: " + furniture.getFurnitureColor().toString() + '\n';
            s += "Покрытие лаком: " + (furniture.isCoveredWithLacquer() ? "Да" : "Нет") + '\n';
            s += "Срок гарантии: " + furniture.getWarranty() + "\n";
            s += "Итоговая цена: " + furniture.getTotalCost();
            textView.setText(s);
        }
        catch (Exception e){
            textView.setText(e.getMessage().toString());
        }
    }

    public void backToMain(View view) {
        finish();
    }
}