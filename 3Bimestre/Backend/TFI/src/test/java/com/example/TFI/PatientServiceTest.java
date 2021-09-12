package com.example.TFI;

import com.example.TFI.DAO.H2Database;
import com.example.TFI.DAO.PatientDAOJDBC;
import com.example.TFI.DAO.UserDAOJDBC;
import com.example.TFI.Models.Patient;
import com.example.TFI.Services.PatientService;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PatientServiceTest {
    private PatientService patientService = new PatientService(H2Database.getInMemoryConnection(), new PatientDAOJDBC(H2Database.getInMemoryConnection()), new UserDAOJDBC(H2Database.getInMemoryConnection()));

    @Test
    public void createPatient() throws SQLException {
        Patient patient = new Patient("test_patient_1", "hola1234", "Ian", "Vaernet");
        patientService.createPatient(patient);
        assertTrue(patient.getId() > 0);
    }

    @Test
    public void getPatient() throws SQLException {
        Patient patient = new Patient("test_patient_2", "hola1234", "Ian", "Vaernet");
        patientService.createPatient(patient);
        Patient patientFromDB = patientService.getPatient(patient.getId());
        assertNotNull(patientFromDB);
    }

    @Test
    public void updatePatient() throws SQLException {
        Patient patient = new Patient("test_patient_3", "hola1234", "Ian", "Vaernet");
        patientService.createPatient(patient);
        patient.setFirstName("Ian Alexis");
        Patient updatedPatient = patientService.updatePatient(patient.getId(), patient);
        assertEquals("Ian Alexis", updatedPatient.getFirstName());
    }

    @Test
    public void deletePatient() throws SQLException {
        Patient patient = new Patient("test_patient_4", "hola1234", "Ian", "Vaernet");
        patientService.createPatient(patient);
        patientService.deletePatient(patient.getId());
        assertNull(patientService.getPatient(patient.getId()));
    }

    @Test
    public void listPatients() throws SQLException {
        Patient patient = new Patient("test_patient_5", "hola1234", "Ian", "Vaernet");
        patientService.createPatient(patient);
        List<Patient> patients = patientService.listPatients();
        assertTrue(patients.size() > 0);
    }
}
