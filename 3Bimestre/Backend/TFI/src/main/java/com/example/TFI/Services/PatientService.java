package com.example.TFI.Services;

import com.example.TFI.DAO.DAO;
import com.example.TFI.Models.Patient;
import com.example.TFI.Models.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PatientService {
    private Connection databaseConnection;
    private DAO<Patient> DAO;
    private final DAO<User> userDAO;

    public PatientService(Connection databaseConnection, DAO<Patient> DAO, DAO<User> userDAO) {
        this.databaseConnection = databaseConnection;
        this.DAO = DAO;
        this.userDAO = userDAO;
    }

    public Patient createPatient(Patient patient) throws SQLException {
        try {
            databaseConnection.setAutoCommit(false);
            int userId = userDAO.create(patient);
            if (userId == 0) return null;
            patient.setId(userId);
            int id = DAO.create(patient);
            if (id == 0) {
                databaseConnection.rollback();
                return null;
            }
            patient.setId(id);
            databaseConnection.commit();
            return patient;
        } finally {
            databaseConnection.setAutoCommit(true);
        }
    }

    public Patient updatePatient(int id, Patient patient) throws SQLException {
        try {
            databaseConnection.setAutoCommit(false);
            User user = userDAO.update(id, patient);
            if (user == null) return null;
            Patient updatedPatient = DAO.update(id, patient);
            if (updatedPatient == null) {
                databaseConnection.rollback();
                return null;
            }
            patient.setId(id);
            databaseConnection.commit();
        } finally {
            databaseConnection.setAutoCommit(true);
        }
        return patient;
    }

    public boolean deletePatient(int id) throws SQLException {
        try {
            databaseConnection.setAutoCommit(false);
            boolean deleted = DAO.delete(id);
            if (!deleted) return false;
            deleted = userDAO.delete(id);
            if (!deleted) {
                databaseConnection.rollback();
                return false;
            }
            databaseConnection.commit();
            return true;
        } finally {
            databaseConnection.setAutoCommit(true);
        }
    }

    public Patient getPatient(int id) {
        return DAO.get(id);
    }

    public List<Patient> listPatients() {
        return DAO.list();
    }
}
