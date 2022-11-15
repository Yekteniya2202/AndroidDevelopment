package com.example.lab8;

public class Reminder {
    private Integer id;
    private String text;
    private Integer daysBefore;

    public Reminder(Integer id, String text, Integer daysBefore) {
        this.id = id;
        this.text = text;
        this.daysBefore = daysBefore;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public Integer getDaysBefore() {
        return daysBefore;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDaysBefore(Integer daysBefore) {
        this.daysBefore = daysBefore;
    }

    @Override
    public String toString() {
        return id + ":" + text + ":" + daysBefore;
    }

    public static Reminder fromString(String str) {
        String[] fields = str.split(":");
        if(fields.length != 3) throw new IllegalArgumentException("Reminder parse error with input string = " + str);
        return new Reminder(Integer.parseInt(fields[0]), fields[1], Integer.parseInt(fields[2]));
    }
}
