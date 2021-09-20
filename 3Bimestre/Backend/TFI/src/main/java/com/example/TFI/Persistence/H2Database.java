package com.example.TFI.Persistence;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Configuration
public class H2Database {
    private static final Logger logger = Logger.getLogger(H2Database.class);
    private static H2Database instance;
    private Connection dbConnection;
    @Autowired
    private DBProperties dbProperties;

    public H2Database() {
    }

    @Bean
    public Connection getConnection() {
        if (instance == null) {
            try {
                dbConnection = DriverManager.getConnection(dbProperties.url, dbProperties.username, dbProperties.password);
                createTables();
            } catch (Exception e) {
                logger.error("Error trying to connect to database: " + e.getMessage());
                e.printStackTrace();
            }
            instance = this;
        }
        return instance.dbConnection;
    }

    private void createTables() {
        try {
            Statement statement = dbConnection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS USERS (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "username VARCHAR(100) UNIQUE, " +
                    "password VARCHAR(100) NOT NULL," +
                    "first_name VARCHAR(100) NOT NULL, " +
                    "last_name VARCHAR(100) NOT NULL, " +
                    "role INTEGER NOT NULL);"
            );
            statement.execute("CREATE TABLE IF NOT EXISTS ADDRESSES (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "street VARCHAR(100) NOT NULL, " +
                    "number INT NOT NULL, " +
                    "floor INT, " +
                    "apartment VARCHAR(20));"
            );
            statement.execute("CREATE TABLE IF NOT EXISTS PATIENTS (" +
                    "id INT PRIMARY KEY, " +
                    "DNI INT NOT NULL, " +
                    "entry_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, " +
                    "address_id INT NOT NULL, " +
                    "FOREIGN KEY(address_id) REFERENCES ADDRESSES(id) ON DELETE CASCADE, " +
                    "FOREIGN KEY(id) REFERENCES USERS(id));"
            );
            statement.execute("CREATE TABLE IF NOT EXISTS DENTISTS (" +
                    "id INT PRIMARY KEY, " +
                    "license_number INT NOT NULL, " +
                    "FOREIGN KEY(id) REFERENCES USERS(id));"
            );
            statement.execute("CREATE TABLE IF NOT EXISTS APPOINTMENTS (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "datetime DATETIME, " +
                    "patient_id INT NOT NULL, " +
                    "dentist_id INT NOT NULL, " +
                    "FOREIGN KEY (patient_id) REFERENCES PATIENTS(id), " +
                    "FOREIGN KEY (dentist_id) REFERENCES DENTISTS(id));"
            );
        } catch (SQLException e) {
            logger.error("Error al intentar crear las tablas de la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
    }
}


@Component
class DBProperties {
    @Value("${spring.datasource.url}")
    public String url;
    @Value("${spring.datasource.username}")
    public String username = "sa";
    @Value("${spring.datasource.password}")
    public String password = "";
}
