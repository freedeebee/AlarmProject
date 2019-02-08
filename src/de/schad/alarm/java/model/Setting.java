package de.schad.alarm.java.model;

import java.io.*;
import java.util.Map;
import java.util.Properties;

public class Setting {

    public final String SETTINGS_FILE_PATH = "src/de/schad/alarm/properties.txt";
    private String alarmPath;

    public String getAlarmPath() {
        return alarmPath;
    }

    public void setAlarmPath(String alarmPath) {
        this.alarmPath = alarmPath;
    }

    public void writeProperty(Map<String, String> properties) {
        Writer writer = null;

        try {
            writer = new FileWriter(SETTINGS_FILE_PATH);
            Properties prop = new Properties( System.getProperties() );
            properties.forEach((key, property) -> prop.setProperty(key, property));
            prop.store(writer, null);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String readProperty(String key) {
        Reader reader = null;
        String property = "";

        try {
            reader = new FileReader(SETTINGS_FILE_PATH);

            Properties prop = new Properties();
            prop.load(reader);
            property = prop.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return property;
    }
}
