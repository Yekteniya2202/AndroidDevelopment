package com.example.lab5;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static ArrayList<Country> countries;

    static int currentTheme = R.style.them1;

    {
        countries = new ArrayList<>();
        countries.add(new Country("Россия", R.drawable.russiasmall, "Территория России в её заявленных границах составляет 17 125 191 км² (первое место по площади среди стран мира), что чуть меньше континента Южная Америка. Расположена полностью в Северном полушарии, бо́льшая часть территории России располагается в Восточном полушарии, лишь восточная часть Чукотского автономного округа располагается в Западном полушарии. "));
        countries.add(new Country("Германия", R.drawable.germanysmall, "Германия граничит на севере с Данией, на востоке с Польшей и Чехией, на юге и юго-востоке с Австрией, на юге со Швейцарией, на юго-западе с Францией, на западе с Люксембургом и Бельгией, на северо-западе с Нидерландами. Омывается с севера Северным и Балтийским морями."));
        countries.add(new Country("Англия", R.drawable.englandsmall, "Англия располагается на острове Великобритания и занимает 2/3 его территории. Площадь ее составляет 130 395 км квадратных. Соседями административной части страны являются Шотландия и Уэльс."));
        countries.add(new Country("Франция", R.drawable.francesmall, "Большая часть Франции расположена в Западной Европе, её материковая часть на северо-востоке граничит с Бельгией, Люксембургом и Германией, на востоке — со Швейцарией, на юго-востоке — с Монако и Италией, на юго-западе — с Испанией и Андоррой, на севере имеется морская граница с Великобританией."));
        countries.add(new Country("Италия", R.drawable.italysmall, "Ита́лия — государство в Южной Европе, в центре Средиземноморья. Общая площадь страны составляет 302 073 км², на её территории расположены южные склоны Альп, Паданская равнина, Апеннинский полуостров (знаменит своей формой, напоминающей сапог), а также острова Сицилия, Сардиния и многочисленные мелкие острова."));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(currentTheme);
        setContentView(R.layout.activity_main);

        TextView countryNameTextView = findViewById(R.id.CountryNameTextView);
        TextView countryDescTextView = findViewById(R.id.CountryDescTextView);
        ImageView countryImageView = findViewById(R.id.CountryImageView);

        Spinner spinner = findViewById(R.id.spinner);

        ArrayList<String> countryNames = new ArrayList<>();
        countries.forEach(country -> countryNames.add(country.getName()));

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, countryNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Country country = countries.get(position);
                countryNameTextView.setText(country.getName());
                countryDescTextView.setText(country.getTerritoryDescription());
                countryImageView.setImageResource(country.getImageId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        };

        spinner.setOnItemSelectedListener(itemSelectedListener);
    }


    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem){

        int id = menuItem.getItemId();
        switch (id){
            case R.id.s1:
                currentTheme = R.style.them1;
                this.recreate();
                return true;
            case R.id.s2:
                currentTheme = R.style.them2;
                this.recreate();
                return true;

            case R.id.vis:
                TextView countryDesc = findViewById(R.id.CountryDescTextView);

                if(countryDesc.getVisibility() == View.VISIBLE){
                    countryDesc.setVisibility(View.INVISIBLE);
                }
                else{
                    countryDesc.setVisibility(View.VISIBLE);
                }

                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setTitle("Отображение изменено!");
                dialog.setContentView(R.layout.dlg);
                dialog.show();
                return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }
}