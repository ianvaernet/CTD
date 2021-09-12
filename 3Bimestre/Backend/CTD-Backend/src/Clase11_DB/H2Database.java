package Clase11_DB;

import java.sql.Connection;
import java.sql.DriverManager;

public class H2Database {
    private static H2Database instance;
    public Connection dbConnection;

    public H2Database() {
        try {
            Class.forName("org.h2.Driver");
            dbConnection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
        } catch (Exception e) {
            System.out.println("Error en la conexión a la base de datos");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        if (instance == null) instance = new H2Database();
        return instance.dbConnection;
    }
}
