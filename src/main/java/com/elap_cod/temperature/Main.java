package com.elap_cod.temperature;

public class Main {
    public static void main(String[] args) {
        TemperatureSensor sensor = new TemperatureSensor();

        TemperatureDisplay display = new TemperatureDisplay();
        TemperatureAlarm alarm = new TemperatureAlarm(10, 30);
        TemperatureLogger logger = new TemperatureLogger();

        sensor.addListener(display);
        sensor.addListener(alarm);
        sensor.addListener(logger);

        sensor.setTemperature(25);
        sensor.setTemperature(5);
        sensor.setTemperature(35);
        sensor.setTemperature(-1);
    }
}
