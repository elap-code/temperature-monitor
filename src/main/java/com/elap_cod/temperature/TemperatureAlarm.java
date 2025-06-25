package com.elap_cod.temperature;

public class TemperatureAlarm implements TemperatureListener {

    private final int lowerThreshold;
    private final int upperThreshold;

    // Konstruktor: setzt die Grenzwerte für Alarm
    public TemperatureAlarm(int lowerThreshold, int upperThreshold) {
        this.lowerThreshold = lowerThreshold;
        this.upperThreshold = upperThreshold;
    }

    @Override
    public void temperatureChanged(int newTemperature) {
        if (newTemperature < lowerThreshold) {
            System.out.println("Alarm: Temperatur zu niedrig! (" + newTemperature + "°C)");
        } else if (newTemperature > upperThreshold) {
            System.out.println("Alarm: Temperatur zu hoch! (" + newTemperature + "°C)");
        }
    }
}
