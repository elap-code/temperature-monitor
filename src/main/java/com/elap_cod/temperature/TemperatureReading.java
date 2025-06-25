package com.elap_cod.temperature;

import java.sql.Timestamp;

public class TemperatureReading {

    private int id;
    private int temperature;
    private Timestamp timestamp;

    public TemperatureReading(int id, int temperature, Timestamp timestamp) {
        this.id = id;
        this.temperature = temperature;
        this.timestamp = timestamp;
    }

    // Getter und Setter
    public int getId() {
        return id;
    }

    public int getTemperature() {
        return temperature;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "TemperatureReading{" +
                "id=" + id +
                ", temperature=" + temperature +
                ", timestamp=" + timestamp +
                '}';
    }
}
