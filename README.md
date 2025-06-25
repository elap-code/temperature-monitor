# Temperature Monitor

## Projektbeschreibung
Temperature Monitor ist eine Java-Anwendung zur Überwachung und Protokollierung von Temperaturwerten in Echtzeit. Das Projekt simuliert die Messung von Temperaturdaten durch Sensoren, speichert diese Werte in einer PostgreSQL-Datenbank und ermöglicht die Anzeige sowie das Auslösen von Alarmen bei Überschreiten definierter Temperaturgrenzen.

## Technologie-Stack
- **Java 11+** - Hauptprogrammiersprache
- **PostgreSQL** - Datenbank für Temperaturwerte
- **Maven** - Build-Management und Dependency-Verwaltung
- **JDBC Driver** - PostgreSQL-Datenbankverbindung

## Features
- Simulation eines Temperatur-Sensors, der Temperaturänderungen meldet
- Listener-Architektur für verschiedene Reaktionen auf Temperaturänderungen (Anzeige, Alarm, Logging)
- Speicherung der Messwerte in einer PostgreSQL-Datenbank via DAO-Schicht
- Alarmfunktion bei Überschreiten von Schwellwerten (zu heiß/zu kalt)
- Ausgabe der aktuellen Werte auf der Konsole mit Zeitstempel
- Automatische Temperaturänderungen in konfigurierbaren Intervallen

## Projektstruktur
- `Main`: Startet die Anwendung und initialisiert Sensor und Listener
- `TemperatureSensor`: Simuliert einen Temperatur-Sensor mit automatischen Änderungen
- `TemperatureListener`: Interface für Beobachter der Temperaturänderungen
- `TemperatureDisplay`: Anzeige der Temperatur auf der Konsole
- `TemperatureAlarm`: Überprüft und meldet Alarm bei Grenzwertüberschreitung
- `TemperatureLogger`: Speichert Temperaturwerte in der Datenbank
- `TemperatureDAO`: Datenbankzugriffsschicht (DAO) für Temperaturwerte
- `TemperatureReading`: Modellklasse für einen Temperatureintrag
- `DatabaseConnector`: Verbindungsmanagement zur PostgreSQL-Datenbank

## Voraussetzungen
- **Java JDK 11** oder höher
- **PostgreSQL** Datenbank (Version 15.13)
- **Maven 3.9.9** für das Build-Management (in IntelliJ integriert)
- **IntelliJ IDEA** oder andere Java IDE
- **pgAdmin 4** (optional zur Datenbankverwaltung)

## Maven Dependencies
```xml
<dependencies>
    <!-- PostgreSQL JDBC Driver -->
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <version>42.6.0</version>
    </dependency>
    <!-- Hier können weitere Dependencies stehen -->
</dependencies>
```

## Installation und Setup

### 1. Datenbank Setup
PostgreSQL installieren und Datenbank erstellen:
```sql
-- Datenbank erstellen
CREATE DATABASE temperature_db;

-- Tabelle für Temperaturmessungen
CREATE TABLE IF NOT EXISTS public.temperature_readings
(
    id integer NOT NULL DEFAULT nextval('temperature_readings_id_seq'::regclass),
    temperature numeric,
    "timestamp" timestamp with time zone NOT NULL,
    CONSTRAINT pk_temperature_readings PRIMARY KEY (id)
);
```

### 2. Projekt Setup
```bash
# Projekt klonen
git clone [repository-url]
cd temperature-monitor

# Maven Dependencies laden (über IntelliJ)
# IntelliJ lädt automatisch alle Dependencies aus der pom.xml
```

### 3. Konfiguration
Datenbankverbindung in `DatabaseConnector.java` anpassen:
```java
private static final String URL = "jdbc:postgresql://localhost:5432/temperature_db";
private static final String USERNAME = "your_username";
private static final String PASSWORD = "your_password";
```

### 4. Anwendung starten
```bash
# In IntelliJ IDEA:
# 1. Projekt öffnen
# 2. Maven Dependencies laden lassen (automatisch)
# 3. Main.java finden (src/main/java/com/elap_cod/temperature/Main.java)
# 4. Rechtsklick auf Main.java → "Run Main.main()"
```

## Beispieldaten

Die Tabelle enthält Temperaturmessungen mit folgender Struktur:

| ID | Temperature (°C) | Timestamp |
|----|------------------|-----------|
| 1  | 23              | 2025-06-24 09:24:34+02 |
| 2  | 19              | 2025-06-24 09:24:34+02 |
| 3  | 30              | 2025-06-24 09:24:34+02 |
| 4  | 25              | 2025-06-24 16:52:11+02 |
| 5  | 5               | 2025-06-24 16:52:12+02 |

```sql
-- Beispieldaten einfügen
INSERT INTO temperature_readings (temperature, "timestamp") VALUES
(23, '2025-06-24 09:24:34+02'),
(19, '2025-06-24 09:24:34+02'),
(30, '2025-06-24 09:24:34+02'),
(25, '2025-06-24 16:52:11+02'),
(5, '2025-06-24 16:52:12+02');
```

## Beispiel-Ausgabe
```
Anzeige: Temperatur ist jetzt 25°C
2025-06-24T16:52:11.863056800: Temperatur geändert auf 25°C
Anzeige: Temperatur ist jetzt 5°C
Alarm: Temperatur zu niedrig! (5°C)
2025-06-24T16:52:12.334736400: Temperatur geändert auf 5°C
Anzeige: Temperatur ist jetzt 35°C
Alarm: Temperatur zu hoch! (35°C)
2025-06-24T16:52:12.375473600: Temperatur geändert auf 35°C
Anzeige: Temperatur ist jetzt 37°C
Alarm: Temperatur zu niedrig! (-1°C)
2025-06-24T16:52:12.440852: Temperatur geändert auf -1°C
```

## Alarm-Schwellwerte
- **Zu hoch**: > 30°C
- **Zu niedrig**: < 0°C
- Alarme werden sowohl auf der Konsole ausgegeben als auch in der Datenbank protokolliert

## Konfiguration

Um sensible Daten wie Datenbank-URL, Benutzername und Passwort nicht im Code zu speichern, werden diese über Umgebungsvariablen verwaltet.
Erstelle dafür im Projekt-Root eine `.env`-Datei (diese ist in `.gitignore` eingetragen und wird nicht mit ins Repository übernommen):

```env
DB_URL=jdbc:postgresql://localhost:5432/temperature_db
DB_USER=postgres
DB_PASSWORD=dein_passwort

```

## Datenbank-Abfragen
Beispiel-Queries für gespeicherte Daten:
```sql
-- Alle Temperaturmessungen anzeigen
SELECT * FROM temperature_readings ORDER BY timestamp DESC;

-- Durchschnittstemperatur der letzten Stunde
SELECT AVG(temperature) FROM temperature_readings 
WHERE timestamp > NOW() - INTERVAL '1 hour';

-- Anzahl der Alarme (Extremwerte)
SELECT COUNT(*) FROM temperature_readings 
WHERE temperature > 30 OR temperature < 0;
```

## Zukünftige Erweiterungen (Roadmap)
- Integration eines einfachen KI-Moduls zur Vorhersage von Temperaturtrends
- Echtzeit-Visualisierung der Temperaturdaten via Web-Frontend
- Unterstützung für mehrere Sensoren und verteilte Systeme
- Alarmierung via E-Mail oder Push-Benachrichtigungen
- Erweiterung um weitere Umweltdaten (Luftfeuchtigkeit, Luftdruck etc.)
- REST-API für externe Datenabfrage
- Konfigurationsdatei für Alarm-Schwellwerte

## Architektur-Pattern
Das Projekt verwendet folgende Design-Patterns:
- **Observer Pattern**: Für die Listener-Architektur
- **DAO Pattern**: Für Datenbankzugriffe

## Fehlerbehandlung
- Automatische Wiederverbindung bei Datenbankfehlern
- Logging von Exceptions
- Graceful Shutdown bei Anwendungsende

## Lizenz
MIT License - siehe LICENSE Datei für Details

## Kontakt
Bei Fragen oder Interesse an Zusammenarbeit gerne  Issue öffnen oder Kontakt aufnehmen.

---
*Dieses Projekt ist Teil einer Lern- und Entwicklungsinitiative im Bereich Java-Programmierung und Datenbankintegration.*