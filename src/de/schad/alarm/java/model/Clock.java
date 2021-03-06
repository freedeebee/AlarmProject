package de.schad.alarm.java.model;

import de.hsrm.mi.eibo.simpleplayer.SimpleAudioPlayer;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Clock implements Runnable {

    private Setting setting;
    private StringProperty time;
    private StringProperty hexTime;
    private AlarmMemory memory = AlarmMemory.getInstance();
    private Thread playSongThread;
    private SimpleMinim minim;
    private SimpleAudioPlayer audioPlayer;
    private boolean isRinging = false;

    public Clock() {
        this.setting = new Setting();
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
                    if(rawTime.equals(alarmtime.getTime()) && alarmtime.isActive()) {
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

    private synchronized void fireAlarm() {
        if (!isRinging) {
            isRinging = true;
            playSongThread = new Thread(() -> {
                minim = new SimpleMinim();

                String radioSetting = setting.readProperty("radio");
                String filepath = setting.readProperty("alarmpath");
                boolean willPlayCustom = (filepath != null) && (radioSetting != null) && radioSetting.equals("custom");
                if(willPlayCustom) {
                    audioPlayer = minim.loadMP3File(filepath);
                } else {
                    audioPlayer = minim.loadMP3File("src/de/schad/alarm/resources/alarmtones/alarm_beep.mp3");
                }


                try {
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
                } catch (Exception e) {
                    // if the file path is incorrect play the default alarm
                    audioPlayer = minim.loadMP3File("src/de/schad/alarm/resources/alarmtones/alarm_beep.mp3");
                    while (audioPlayer.isPlaying() && !playSongThread.isInterrupted()) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException ex) {
                            playSongThread.interrupt();
                        }
                    }
                    if (!playSongThread.isInterrupted()) {
                        if (!(audioPlayer.isPlaying())) {
                            audioPlayer.play();
                            audioPlayer.loop();
                        }
                    }
                }

            });
            playSongThread.start();
        }
    }

    /**
     * Let's a ringing alarm snooze for 5 minutes
     */
    public void sleep() {
        if(isRinging) {
            alarmOff();
            Thread snoozeThread = new Thread(() -> {

                    try {
                        Thread.sleep(5 * 60 * 1000); // 5 Minutes
                        fireAlarm();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

            });
            snoozeThread.start();
        }
    }

    public void alarmOff() {
        if(minim != null) {
            minim.stop();
            playSongThread.interrupt();
            isRinging = false;
        }
    }

}
