package com.example.TFI.Services;

import com.example.TFI.Models.Dentist;
import com.example.TFI.Models.Role;
import com.example.TFI.Persistence.IDAO;
import com.example.TFI.Models.Patient;
import com.example.TFI.Models.User;
import com.example.TFI.Persistence.IPatientRepository;
import com.example.TFI.Persistence.IUserDAO;
import com.example.TFI.Persistence.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    //  ORM repositories:
    @Autowired
    private IPatientRepository patientRepository;
    @Autowired
    private IUserRepository userRepository;
    //  DAO with JDBC:
    @Autowired
    private Connection databaseConnection;
    @Autowired
    private IDAO<Patient> patientDAO;
    @Autowired
    private IUserDAO userDAO;


    @Transactional
    public Patient createPatient(Patient patient) {
        patient.setRole(Role.PATIENT);
        User user = userRepository.save(patient);
        patient = patientRepository.save(patient);
        return patient;
    }

    public Patient createPatientWithDAO(Patient patient) throws SQLException {
        try {
            databaseConnection.setAutoCommit(false);
            int userId = userDAO.create(patient);
            if (userId == 0) return null;
            patient.setId(userId);
            int id = patientDAO.create(patient);
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

    @Transactional
    public Patient updatePatient(int id, Patient patient) {
        patient.setId(id);
        User user = userRepository.save(patient);
        patient = patientRepository.save(patient);
        return patient;
    }

    public Patient updatePatientWithDAO(int id, Patient patient) throws SQLException {
        try {
            databaseConnection.setAutoCommit(false);
            User user = userDAO.update(id, patient);
            if (user == null) return null;
            Patient updatedPatient = patientDAO.update(id, patient);
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

    public boolean deletePatient(int id) {
        userRepository.deleteById(id);
        return true;
    }

    public boolean deletePatientWithDAO(int id) throws SQLException {
        try {
            databaseConnection.setAutoCommit(false);
            boolean deleted = patientDAO.delete(id);
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
        Optional<Patient> patient = patientRepository.findById(id);
        if (patient.isEmpty()) return null;
        return patient.get();
    }

    public Patient getPatientWithDAO(int id) {
        return patientDAO.get(id);
    }

    public List<Patient> listPatients() {
        return patientRepository.findAll();
    }

    public List<Patient> listPatientsWithDAO() {
        return patientDAO.list();
    }
}
