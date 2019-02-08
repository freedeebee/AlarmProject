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

    public boolean addAlarmTime(AlarmTime time) {
        boolean isAlreadyIn = false;
        for(AlarmTime time1: times) {
            if(time1.getTime().equals(time.getTime())) {
                isAlreadyIn = true;
            }
        }
        if(!isAlreadyIn) {
            times.add(time);
        }
        return isAlreadyIn;
    }

    public void removeAlarmTime(AlarmTime time) {
        times.remove(time);
    }

    public List<AlarmTime> getTimes() {
        return times;
    }
}
