package de.schad.alarm.java.model;

import de.hsrm.mi.eibo.simpleplayer.SimpleAudioPlayer;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Clock implements Runnable {

    private StringProperty time;
    private StringProperty hexTime;
    private AlarmMemory memory = AlarmMemory.getInstance();
    private Thread playSongThread;
    private SimpleMinim minim;
    private SimpleAudioPlayer audioPlayer;
    private boolean isRinging = false;

    public Clock() {
        time = new SimpleStringProperty();
        hexTime = new SimpleStringProperty();
    }

    @Override
    public void run() {
        try {

            Calendar calendar;
            String rawTime;

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


                for(AlarmTime alarmtime: memory.getTimes()) {
                    if(rawTime.equals(alarmtime.getTime()) && alarmtime.isActive() && !isRinging) {
                        fireAlarm();
                        break;
                    }
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

    public void fireAlarm() {
        isRinging = true;
        playSongThread = new Thread(() -> {
            minim = new SimpleMinim();
            audioPlayer = minim.loadMP3File("src/de/schad/alarm/resources/alarmtones/alarm_beep.mp3");

            while (audioPlayer.isPlaying() && !playSongThread.isInterrupted()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    playSongThread.interrupt();
                }
            }
            if (!playSongThread.isInterrupted()) {
                if (!(audioPlayer.isPlaying())) {
                    audioPlayer.play();
                    audioPlayer.loop();
                }
            }
        });
        playSongThread.start();
    }

    public void sleep() {
        // TODO: Sleep
    }

    public void alarmOff() {
        if(minim != null) {
            minim.stop();
            playSongThread.interrupt();
            isRinging = false;
        }
    }

}
