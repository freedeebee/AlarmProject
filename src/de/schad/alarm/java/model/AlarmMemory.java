package de.schad.alarm.java.model;

import java.util.ArrayList;
import java.util.List;

public class AlarmMemory {

    private List<AlarmTime> times;

    private static AlarmMemory instance = null;
    private AlarmMemory() {
        this.times = new ArrayList<>();
    }
    public static AlarmMemory getInstance() {
        if(instance == null) {
            instance = new AlarmMemory();
        }
        return instance;
    }

    public void addAlarmTime(AlarmTime time) {
        times.add(time);
    }

    public void removeAlarmTime(AlarmTime time) {
        times.remove(time);
    }

    public List<AlarmTime> getTimes() {
        return times;
    }
}
