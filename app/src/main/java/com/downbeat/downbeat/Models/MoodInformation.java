package com.downbeat.downbeat.Models;

public class MoodInformation {

    String time;
    String date;
    String mood;

    public MoodInformation(String time, String date, String mood) {
        this.time = time;
        this.date = date;
        this.mood = mood;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }
}
