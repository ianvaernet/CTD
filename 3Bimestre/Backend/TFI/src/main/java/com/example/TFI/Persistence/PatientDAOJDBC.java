package com.example.TFI.Persistence;

import com.example.TFI.Models.Address;
import com.example.TFI.Models.Patient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PatientDAOJDBC implements IDAO<Patient> {
    private static final Logger logger = Logger.getLogger(PatientDAOJDBC.class);
    @Autowired
    private Connection dbConnection;

    public PatientDAOJDBC() {
    }

    @Override
    public int create(Patient newPatient) {
        logger.debug("Creando paciente...");
        int id = 0;
        try {
            PreparedStatement statement = dbConnection.prepareStatement("INSERT INTO ADDRESSES (street, number, floor, apartment) VALUES (?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, newPatient.getAddress().getStreet());
            statement.setInt(2, newPatient.getAddress().getNumber());
            if (newPatient.getAddress().getFloor() != null) statement.setInt(3, newPatient.getAddress().getFloor());
            else statement.setNull(3, Types.INTEGER);
            statement.setString(4, newPatient.getAddress().getApartment());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) newPatient.getAddress().setId(generatedKeys.getInt(1));
            statement = dbConnection.prepareStatement("INSERT INTO PATIENTS (id, DNI, entry_date, address_id) VALUES (?, ?, current_timestamp, ?);", Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, newPatient.getId());
            statement.setInt(2, newPatient.getDNI());
            statement.setInt(3, newPatient.getAddress().getId());
            statement.executeUpdate();
            generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
                logger.debug("Paciente creado con éxito: ID=" + id);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public Patient update(int id, Patient updatedPatient) {
        logger.debug("Actualizando paciente...");
        try {
            if (updatedPatient.getAddress() != null) {
                PreparedStatement statement = dbConnection.prepareStatement("UPDATE ADDRESSES SET street = ?, number = ?, floor = ?, apartment = ? WHERE id = ?;");
                statement.setString(1, updatedPatient.getFirstName());
                statement.setString(2, updatedPatient.getAddress().getStreet());
                statement.setInt(3, updatedPatient.getAddress().getNumber());
                statement.setString(4, updatedPatient.getAddress().getApartment());
                statement.setInt(5, updatedPatient.getAddress().getId());
            }
            PreparedStatement statement = dbConnection.prepareStatement("UPDATE PATIENTS SET DNI = ? WHERE id = ?;");
            statement.setInt(1, updatedPatient.getDNI());
            statement.setInt(2, id);
            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                logger.debug("Paciente actualizado con éxito: " + updatedPatient);
                return updatedPatient;
            } else {
                logger.warn("No existe ningún paciente con el ID proporcionado");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return updatedPatient;
    }

    @Override
    public boolean delete(int id) {
        logger.debug("Eliminando paciente...");
        try {
            PreparedStatement statement = dbConnection.prepareStatement("DELETE FROM PATIENTS WHERE id = ?;");
            statement.setInt(1, id);
            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                logger.debug("Paciente eliminado con éxito: ID=" + id);
                return true;
            } else {
                logger.warn("No existe ningún paciente con el ID proporcionado");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Patient get(int id) {
        logger.debug("Obteniendo paciente...");
        Patient patient = null;
        try {
            PreparedStatement statement = dbConnection.prepareStatement("SELECT * FROM PATIENTS INNER JOIN USERS ON PATIENTS.id = USERS.id INNER JOIN ADDRESSES ON PATIENTS.address_id = ADDRESSES.id WHERE PATIENTS.id = ?;");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                Address address = new Address(result.getString("street"), result.getInt("number"), result.getInt("floor"), result.getString("apartment"));
                patient = new Patient(result.getString("username"), result.getString("password"), result.getString("first_name"), result.getString("last_name"), result.getInt("DNI"), result.getDate("entry_date"), address);
                patient.setId(result.getInt("id"));
                logger.debug(patient);
            } else {
                logger.warn("No existe ningún paciente con el ID proporcionado");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return patient;
    }

    @Override
    public List<Patient> list() {
        logger.debug("Listando pacientes...");
        List<Patient> patients = new ArrayList<>();
        try {
            Statement statement = dbConnection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM PATIENTS INNER JOIN USERS ON PATIENTS.id = USERS.id;");
            while (result.next()) {
                Patient patient = new Patient(result.getString("username"), result.getString("password"), result.getString("first_name"), result.getString("last_name"), result.getInt("DNI"), result.getDate("entry_date"), null);
                patient.setId(result.getInt("id"));
                patients.add(patient);
                logger.debug(patient);
            }
        } catch (SQLException e) {
            logger.error("Error al listar pacientes");
            e.printStackTrace();
        }
        return patients;
    }
}
