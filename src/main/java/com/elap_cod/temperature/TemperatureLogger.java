package com.elap_cod.temperature;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class TemperatureLogger implements TemperatureListener {

    private TemperatureDAO dao = new TemperatureDAO();

    @Override
    public void temperatureChanged(int newTemperature) {
        System.out.println(LocalDateTime.now() + ": Temperatur geändert auf " + newTemperature + "°C");

        // Zeitstempel der aktuellen Zeit
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());

        try {
            // Temperatur und Zeitstempel in die DB speichern
            dao.insertTemperature(newTemperature, timestamp);
        } catch (Exception e) {
            System.err.println("Fehler beim Speichern der Temperatur: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
