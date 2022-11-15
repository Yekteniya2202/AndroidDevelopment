package com.example.lab8;

import android.app.Activity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.TextView;

import java.util.Map;

public class ConfigActivity extends Activity {

    int widgetID = AppWidgetManager.INVALID_APPWIDGET_ID;
    Intent resultValue;

    Context thisContext;
    int awID;
    AppWidgetManager awManager;
    RemoteViews awRV;

    final String LOG_TAG = "myLogs";

    static SharedPreferences sp;


    public final static String WIDGET_REM = "widget_rem";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config);

        sp = getSharedPreferences(WIDGET_REM, MODE_PRIVATE);
        Intent intent = getIntent();
        Bundle bundleExtras = intent.getExtras();
        if (bundleExtras != null) {
            awID = bundleExtras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        } else {
            finish();
        }

        thisContext = this.getApplicationContext();
        awManager = AppWidgetManager.getInstance(thisContext);
        awRV = new RemoteViews(thisContext.getPackageName(), R.layout.widget);

        Map<String, ?> allReminders = sp.getAll();
        TextView reminderListTextView = findViewById(R.id.textViewReminderList);
        for (Object value : allReminders.values()) {
            reminderListTextView.setText(reminderListTextView.getText().toString() + '\n' + value.toString());
        }

    }


    public void addOrEditReminder(View v) {
        SharedPreferences.Editor editor = sp.edit();

        EditText editTextId = findViewById(R.id.editTextReminderNumber);
        String reminderNumberInEditText = editTextId.getText().toString();
        System.out.println(reminderNumberInEditText);
        String reminderNumberInSharedPreferences = sp.getString(reminderNumberInEditText, null);
        System.out.println(reminderNumberInSharedPreferences);

        Log.d(LOG_TAG, "1 " + reminderNumberInEditText);
        Log.d(LOG_TAG, "2 " + reminderNumberInSharedPreferences);

        //Добавляем
        if (reminderNumberInSharedPreferences == null) {
            EditText editTextReminder = findViewById(R.id.editTextReminder);
            EditText editTextDaysBefore = findViewById(R.id.editTextDaysBefore);
            String reminderText = editTextReminder.getText().toString();
            Integer daysBefore = Integer.parseInt(editTextDaysBefore.getText().toString());

            editor.putString(reminderNumberInEditText, new Reminder(Integer.parseInt(reminderNumberInEditText), reminderText, daysBefore).toString());
        }
        //редактируем
        else {
            Reminder reminder = Reminder.fromString(sp.getString(reminderNumberInSharedPreferences.split(":")[0], null));
            EditText editTextReminder = findViewById(R.id.editTextReminder);
            EditText editTextDaysBefore = findViewById(R.id.editTextDaysBefore);
            String reminderText = editTextReminder.getText().toString();
            Integer daysBefore = Integer.parseInt(editTextDaysBefore.getText().toString());

            reminder.setText(reminderText);
            reminder.setDaysBefore(daysBefore);

            editor.putString(reminder.getId().toString(), reminder.toString());
        }

        editor.apply();
        Intent intentBtnPwr = new Intent(thisContext, ConfigActivity.class);
        intentBtnPwr.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, awID);
        PendingIntent pi = PendingIntent.getActivity(thisContext, awID, intentBtnPwr, PendingIntent.FLAG_UPDATE_CURRENT);
        awRV.setOnClickPendingIntent(R.id.button, pi);
        // added end */
        awManager.updateAppWidget(awID, awRV);

        Intent resultIntent = new Intent();
        resultIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, awID);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}