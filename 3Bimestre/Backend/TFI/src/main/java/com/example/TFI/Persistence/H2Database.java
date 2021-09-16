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
                    "username VARCHAR(255) UNIQUE, " +
                    "password VARCHAR(255) NOT NULL," +
                    "first_name VARCHAR(255) NOT NULL, " +
                    "last_name VARCHAR(255) NOT NULL, " +
                    "role INTEGER NOT NULL);"
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
        } catch (SQLException e) {
            logger.error("Error al intentar crear las tablas de la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
        try {
            Statement statement = dbConnection.createStatement();
            statement.execute("INSERT INTO USERS (username, password, role, first_name, last_name) VALUES " +
                    "('admin', 'admin', 'ADMIN', 'Admin', 'User');");
        } catch (Exception e){
            System.out.println();
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

    @Autowired
    public DBProperties(@Value("${spring.datasource.url}") String url, @Value("${spring.datasource.username}") String username, @Value("${spring.datasource.password}") String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }
}
