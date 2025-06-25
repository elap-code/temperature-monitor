package com.elap_cod.temperature;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnectionTest {

    public static void main(String[] args) {
        try (Connection conn = DatabaseConnector.getConnection()) {
            if (conn != null) {
                System.out.println("Verbindung zur Datenbank erfolgreich!");
            } else {
                System.out.println("Verbindung fehlgeschlagen.");
            }
        } catch (SQLException e) {
            System.out.println("Fehler bei der Verbindung:");
            e.printStackTrace();
        }
    }
}

