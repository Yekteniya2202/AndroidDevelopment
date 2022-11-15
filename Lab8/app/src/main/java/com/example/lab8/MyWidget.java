package com.example.lab8;

import static android.content.Context.MODE_PRIVATE;
import static java.time.temporal.ChronoUnit.DAYS;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MyWidget extends AppWidgetProvider {

    final static String LOG_TAG = "widgetLog";
    public ArrayList<Reminder> reminders = new ArrayList<>();

    @Override
    public void onEnabled(Context context) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);

        AppWidgetManager awm = AppWidgetManager.getInstance(context);
        ComponentName compName = new ComponentName(context, MyWidget.class);
        int[] widgetIds = awm.getAppWidgetIds(compName);

        for (int widgetId : widgetIds) {
            Intent intentBtnPwr = new Intent(context, ConfigActivity.class);
            intentBtnPwr.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
            PendingIntent pi = PendingIntent.getActivity(context, widgetId, intentBtnPwr, PendingIntent.FLAG_MUTABLE);
            remoteViews.setOnClickPendingIntent(R.id.button, pi);
            awm.updateAppWidget(widgetId, remoteViews);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Log.d(LOG_TAG, "onUpdate " + Arrays.toString(appWidgetIds));

        for (int appWidgetId : appWidgetIds) {
            RemoteViews widgetView = new RemoteViews(context.getPackageName(), R.layout.widget);
            widgetView.setTextViewText(R.id.tv, "До нового года\n" + daysBeforeNewYear() + " дней");

            writeValidReminders(context, widgetView);

            Log.d(LOG_TAG, "onUpdate " + daysBeforeNewYear());
            appWidgetManager.updateAppWidget(appWidgetId, widgetView);
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        Log.d(LOG_TAG, "onDeleted " + Arrays.toString(appWidgetIds));

    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
        Log.d(LOG_TAG, "onDisabled");
    }

    private void writeValidReminders(Context context, RemoteViews widgetView) {
        SharedPreferences sp = context.getSharedPreferences(ConfigActivity.WIDGET_REM, MODE_PRIVATE);
        Map<String, ?> allReminders = sp.getAll();

        for (Object value : allReminders.values()) {
            reminders.add(Reminder.fromString(value.toString()));
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (Reminder reminder : reminders) {
            if (reminder.getDaysBefore() < Integer.parseInt(daysBeforeNewYear())) {
                stringBuilder.append(reminder);
                stringBuilder.append('\n');
            }
        }
        System.out.println(stringBuilder);
        widgetView.setTextViewText(R.id.textViewReminder, stringBuilder.toString());
    }
    private String daysBeforeNewYear() {
        LocalDate newYear = LocalDate.of(2023, 1, 1);
        LocalDate now = LocalDate.now();
        return Long.toString(ChronoUnit.DAYS.between(now, newYear));
    }
}