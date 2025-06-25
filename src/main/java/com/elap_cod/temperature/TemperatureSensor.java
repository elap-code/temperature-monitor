package com.elap_cod.temperature;

import java.util.ArrayList;
import java.util.List;

public class TemperatureSensor {

    private int temperature;  // aktueller Wert
    private final List<TemperatureListener> listeners = new ArrayList<>();

    // Listener hinzufügen
    public void addListener(TemperatureListener listener) {
        listeners.add(listener);
    }

    // Listener entfernen
    public void removeListener(TemperatureListener listener) {
        listeners.remove(listener);
    }

    // Temperatur setzen und Listener benachrichtigen
    public void setTemperature(int newTemperature) {
        this.temperature = newTemperature;
        notifyListeners();
    }

    // Benachrichtigt alle registrierten Listener
    private void notifyListeners() {
        for (TemperatureListener listener : listeners) {
            listener.temperatureChanged(temperature);
        }
    }
}
