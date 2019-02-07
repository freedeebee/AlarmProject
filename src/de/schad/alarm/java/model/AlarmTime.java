package de.schad.alarm.java.model;

public class AlarmTime {

    private String time;
    private boolean isActive;

    public AlarmTime(String time) {
        this.time = time;
        this.isActive = true;
    }

    public String getTime() {
        return time;
    }

    public boolean isActive() {
        return isActive;
    }

    public void toggleActive() {
        isActive = !isActive;
    }
}
