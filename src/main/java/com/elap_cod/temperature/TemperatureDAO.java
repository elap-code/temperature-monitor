package com.elap_cod.temperature;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TemperatureDAO {

    // Methode, um einen neuen Temperatureintrag einzufügen
    public void insertTemperature(int temperature, Timestamp timestamp) throws SQLException {
        String sql = "INSERT INTO temperature_readings (temperature, timestamp) VALUES (?, ?)";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, temperature);       // 1. Parameter: Temperatur
            pstmt.setTimestamp(2, timestamp);   // 2. Parameter: Zeitstempel

            pstmt.executeUpdate();               // SQL-Befehl ausführen (INSERT)
        }
    }

    // Methode, um alle Temperatureinträge aus der DB auszulesen
    public List<TemperatureReading> getAllReadings() throws SQLException {
        List<TemperatureReading> readings = new ArrayList<>();
        String sql = "SELECT * FROM temperature_readings ORDER BY id ASC";

        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                // Jeden Datensatz auslesen und in ein TemperatureReading-Objekt speichern
                TemperatureReading tr = new TemperatureReading(
                        rs.getInt("id"),
                        rs.getInt("temperature"),
                        rs.getTimestamp("timestamp")
                );
                readings.add(tr);  // Objekt zur Liste hinzufügen
            }
        }

        return readings;  // Liste mit allen Einträgen zurückgeben
    }
}

