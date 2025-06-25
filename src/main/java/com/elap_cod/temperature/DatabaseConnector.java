package com.elap_cod.temperature;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    // URL der Datenbank aus der Umgebungsvariable lesen
    private static final String URL = System.getenv("DB_URL");

    // Benutzername aus der Umgebungsvariable lesen
    private static final String USER = System.getenv("DB_USER");

    // Passwort aus der Umgebungsvariable lesen
    private static final String PASSWORD = System.getenv("DB_PASSWORD");

    // Methode, die eine Verbindung zur Datenbank herstellt
    public static Connection getConnection() throws SQLException {
        if (URL == null || USER == null || PASSWORD == null) {
            throw new IllegalStateException("Datenbank-Zugangsdaten sind nicht gesetzt. Bitte Umgebungsvariablen prüfen.");
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);


    }

}
