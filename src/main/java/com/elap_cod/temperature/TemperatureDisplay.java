package com.elap_cod.temperature;

public class TemperatureDisplay implements TemperatureListener {

    @Override
    public void temperatureChanged(int newTemperature) {
        System.out.println("Anzeige: Temperatur ist jetzt " + newTemperature + "°C");
    }
}

