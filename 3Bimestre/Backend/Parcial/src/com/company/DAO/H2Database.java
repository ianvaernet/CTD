package com.company.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.log4j.Logger;

// Clase Singleton para crear una única conexión a la BD
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

    // BD en memoria para utilizar durante los tests
    public static Connection getInMemoryConnection() {
        if (instance == null) instance = new H2Database("jdbc:h2:mem:test");
        return instance.dbConnection;
    }

    // Inicialización
    private void createTables() {
        try {
            Statement statement = dbConnection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS DENTIST ("+
                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "license_number INT, " +
                    "first_name VARCHAR(255), " +
                    "last_name VARCHAR(255));"
            );
        } catch (SQLException e) {
            logger.error("Error trying to create database tables: " + e.getMessage());
            e.printStackTrace();
        }
    }
}