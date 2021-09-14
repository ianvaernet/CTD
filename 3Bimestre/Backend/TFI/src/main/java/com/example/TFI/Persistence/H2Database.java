package com.example.TFI.Persistence;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class H2Database {
    private static final Logger logger = Logger.getLogger(H2Database.class);
    private static H2Database instance;
    private Connection dbConnection;
    private String url;
    private String username = "sa";
    private String password = "";

    private H2Database(String url) {
        this.url = url;
        try {
            dbConnection = DriverManager.getConnection(this.url, this.username, this.password);
            createTables();
        } catch (Exception e) {
            logger.error("Error trying to connect to database: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        if (instance == null) instance = new H2Database("jdbc:h2:tcp://localhost/~/test");
        return instance.dbConnection;
    }

    public static Connection getInMemoryConnection() {
        if (instance == null) instance = new H2Database("jdbc:h2:mem:test");
        return instance.dbConnection;
    }

    private void createTables() {
        try {
            Statement statement = dbConnection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS USERS (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "username VARCHAR(255) UNIQUE, " +
                    "password VARCHAR(255) NOT NULL," +
                    "first_name VARCHAR(255) NOT NULL, " +
                    "last_name VARCHAR(255) NOT NULL, " +
                    "role VARCHAR(30) NOT NULL);"
            );
            statement.execute("CREATE TABLE IF NOT EXISTS PATIENTS (" +
                    "id INT PRIMARY KEY, " +
                    "FOREIGN KEY(id) REFERENCES USERS(id));"
            );
            statement.execute("CREATE TABLE IF NOT EXISTS DENTISTS (" +
                    "id INT PRIMARY KEY, " +
                    "license_number INT NOT NULL, " +
                    "FOREIGN KEY(id) REFERENCES USERS(id));"
            );
//            statement.execute("INSERT INTO USERS (username, password, role, first_name, last_name) VALUES " +
//                    "('ian_admin', 'hola1234', 'ADMIN', 'Ian', 'Vaernet'), " +
//                    "('ian_paciente', 'hola1234', 'PATIENT', 'Ian', 'Vaernet'), " +
//                    "('ian_odontologo', 'hola1234', 'DENTIST', 'Ian', 'Vaernet');");
//            statement.execute("INSERT INTO PATIENTS (id) VALUES (2);");
//            statement.execute("INSERT INTO DENTISTS (id, license_number) VALUES (3, 3983);");
        } catch (SQLException e) {
            logger.error("Error al intentar crear las tablas de la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
