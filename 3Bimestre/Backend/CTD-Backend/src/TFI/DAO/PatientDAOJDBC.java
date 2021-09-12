package TFI.DAO;

import TFI.Models.Patient;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAOJDBC implements DAO<Patient> {
    private static final Logger logger = Logger.getLogger(PatientDAOJDBC.class);
    private final Connection dbConnection;

    public PatientDAOJDBC() {
        this.dbConnection = H2Database.getConnection();
    }

    public PatientDAOJDBC(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public int create(Patient newPatient) {
        int id = 0;
        try {
            PreparedStatement statement = dbConnection.prepareStatement("INSERT INTO PATIENT (username, password, first_name, last_name) VALUES (?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, newPatient.getUsername());
            statement.setString(2, newPatient.getPassword());
            statement.setString(3, newPatient.getFirstName());
            statement.setString(4, newPatient.getLastName());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public Patient update(int id, Patient updatedPatient) {
        try {
            PreparedStatement statement = dbConnection.prepareStatement("UPDATE PATIENT SET username = ?, password = ?, first_name = ?, last_name = ? WHERE ID = ?;");
            statement.setString(1, updatedPatient.getUsername());
            statement.setString(2, updatedPatient.getPassword());
            statement.setString(3, updatedPatient.getFirstName());
            statement.setString(4, updatedPatient.getLastName());
            statement.setInt(5, id);
            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                return updatedPatient;
            } else {
                System.out.println("No existe ningún paciente con el ID proporcionado");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement statement = dbConnection.prepareStatement("DELETE FROM PATIENT WHERE ID = ?;");
            statement.setInt(1, id);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                System.out.println("No existe ningún paciente con el ID proporcionado");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public Patient get(int id) {
        Patient patient = null;
        try {
            PreparedStatement statement = dbConnection.prepareStatement("SELECT * FROM PATIENT WHERE ID = ?;");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                patient = new Patient(result.getString("username"), result.getString("password"), result.getString("first_name"), result.getString("last_name"));
                patient.setId(result.getInt("id"));
            } else {
                System.out.println("No existe ningún paciente con el ID proporcionado");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return patient;
    }

    @Override
    public List<Patient> list() {
        List<Patient> patients = new ArrayList<>();
        try {
            Statement statement = dbConnection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM PATIENT;");
            while (result.next()) {
                Patient patient = new Patient(result.getString("username"), result.getString("password"), result.getString("first_name"), result.getString("last_name"));
                patient.setId(result.getInt("id"));
                patients.add(patient);
            }
        } catch (SQLException e) {
            logger.error("Error al listar la table PATIENT");
            e.printStackTrace();
        }
        return patients;
    }
}
