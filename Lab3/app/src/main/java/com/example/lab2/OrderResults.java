package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
        printNewFurniture(furniture);
    }

    private void printNewFurniture(Furniture furniture) {
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

    public void printOldOrders(View view) {
        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("furniture.db", MODE_PRIVATE, null);
        TextView textView = findViewById(R.id.totalTextView);
        textView.setText("");
        try {
            Cursor query = db.rawQuery("SELECT * FROM furniture", null);
            while(query.moveToNext()) {
                String s = "Состав заказа:\n";
                s += "Тип мебели: " + query.getString(0) + '\n';
                s += "Цвет: " + query.getString(2) + '\n';
                s += "Материал: " + query.getString(1) + '\n';
                s += "Покрытие лаком: " + query.getString(3) + '\n';
                s += "Срок гарантии: " + query.getString(4) + "\n";
                s += "Итоговая цена: " + query.getString(5);
                s += '\n';
                textView.setText(textView.getText() + "\n" + s);
            }
        }
        catch (Exception e){
            textView.setText(e.getMessage().toString());
        }
    }
}