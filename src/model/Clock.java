package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Clock implements Runnable {

    private StringProperty time;
    private StringProperty hexTime;
    private String rawTime;
    private Calendar calendar;
    private String alarmTime;

    public Clock() {
        time = new SimpleStringProperty();
        hexTime = new SimpleStringProperty();
        alarmTime = "110100";
    }

    @Override
    public void run() {
        try {
            while (true) {
                calendar = new GregorianCalendar();
                int second = calendar.get(Calendar.SECOND);
                int minute = calendar.get(Calendar.MINUTE);
                int hour = calendar.get(Calendar.HOUR);
                int amPm = calendar.get(Calendar.AM_PM);

                if(amPm == 1) {
                    hour += 12;
                }

                String fHour = String.format("%02d", hour);
                String fMinute = String.format("%02d", minute);
                String fSecond = String.format("%02d", second);

                rawTime = fHour + fMinute +fSecond;
                time.setValue(fHour + ":" + fMinute + ":" + fSecond);
                hexTime.setValue("#" + fHour + fMinute + fSecond);

                if(rawTime.equals(alarmTime)) {
                    fireAlarm();
                }

                Thread.sleep(1000);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public StringProperty getTimeProperty() {
        return this.time;
    }

    public StringProperty getHexTimeProperty() {
        return this.hexTime;
    }

    public void setAlarm(String time) {
        System.out.println("Alarm set so " + time);
        this.alarmTime = time;
    }

    public void fireAlarm() {
        System.out.println("Alarm!");
    }

}
