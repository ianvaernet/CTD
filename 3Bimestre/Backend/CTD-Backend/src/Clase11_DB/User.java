package Clase11_DB;

import java.sql.*;

import Clase10_Logs.Main;
import org.apache.log4j.Logger;

public class User {
    private static final Logger logger = Logger.getLogger(Main.class);
    private long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;

    public User(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        Connection dbConnection = H2Database.getConnection();
        try {
            PreparedStatement statement = dbConnection.prepareStatement("INSERT INTO USER (first_name, last_name, username, password) VALUES (?, ?, ?, ?);");
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, username);
            statement.setString(4, password);
            statement.execute();
        } catch (SQLException throwables) {
            logger.error(throwables.getMessage());
            throwables.printStackTrace();
        }
    }

    public static void createTable() {
        try {
            Connection dbConnection = H2Database.getConnection();
            Statement statement = dbConnection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS USER("+
                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "first_name VARCHAR(255), " +
                    "last_name VARCHAR(255), "+
                    "username VARCHAR(255), "+
                    "password VARCHAR(255));");
        } catch (SQLException throwables) {
            logger.error("Error en la creación de la tabla User");
            throwables.printStackTrace();
        }
    }

    public static void list() {
        try {
            Connection dbConnection = H2Database.getConnection();
            Statement statement = dbConnection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM USER;");
            while(result.next()) {
                System.out.println("ID: " + result.getInt("id"));
                System.out.println("First name: " + result.getString("first_name"));
                System.out.println("Last name: " + result.getString("last_name"));
                System.out.println("Username: " + result.getString("username"));
                System.out.println();
            }
        } catch (SQLException throwables) {
            logger.error("Error al listar la tabla User");
            throwables.printStackTrace();
        }
    }

    public static void get(int id) {
        try {
            Connection dbConnection = H2Database.getConnection();
            PreparedStatement statement = dbConnection.prepareStatement("SELECT * FROM USER WHERE ID = ?;");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                System.out.println("ID: " + result.getInt("id"));
                System.out.println("First name: " + result.getString("first_name"));
                System.out.println("Last name: " + result.getString("last_name"));
                System.out.println("Username: " + result.getString("username"));
            } else {
                System.out.println("No existe ningún usuario con el ID proporcionado");
            }
        } catch (SQLException throwables) {
            logger.error(throwables.getMessage());
            throwables.printStackTrace();
        }
    }
}
