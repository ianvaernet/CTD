package com.example.TFI;

import com.example.TFI.Models.Patient;
import com.example.TFI.Services.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PatientServiceTest {
    @Autowired
    private PatientService patientService;

    @Test
    public void createPatient() throws SQLException {
        Patient patient = new Patient("test_patient_1", "hola1234", "Ian", "Vaernet");
        Patient createdPatient = patientService.createPatient(patient);
        assertNotNull(createdPatient);
        assertTrue(createdPatient.getId() > 0);
    }
    @Test
    public void createPatientWithDAO() throws SQLException {
        Patient patient = new Patient("test_patient_dao_1", "hola1234", "Ian", "Vaernet");
        Patient createdPatient = patientService.createPatientWithDAO(patient);
        assertNotNull(createdPatient);
        assertTrue(createdPatient.getId() > 0);
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
