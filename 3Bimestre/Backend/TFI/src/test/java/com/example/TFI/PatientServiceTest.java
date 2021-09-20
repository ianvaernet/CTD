package com.example.TFI;

import com.example.TFI.Models.Address;
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
    public void createPatient() {
        Address address = new Address("Donovan", 656);
        Patient patient = new Patient("test_patient_1", "hola1234", "Ian", "Vaernet", 29582510, null, address);
        Patient createdPatient = patientService.createPatient(patient);
        assertNotNull(createdPatient);
        assertTrue(createdPatient.getId() > 0);
    }
    @Test
    public void createPatientWithDAO() throws SQLException {
        Address address = new Address("Donovan", 656);
        Patient patient = new Patient("test_patient_dao_1", "hola1234", "Ian", "Vaernet", 52365128, null, address);
        System.out.println(patient.getAddress());
        Patient createdPatient = patientService.createPatientWithDAO(patient);
        assertNotNull(createdPatient);
        assertTrue(createdPatient.getId() > 0);
    }

    @Test
    public void getPatient() {
        Patient readedPatient = patientService.getPatient(5);
        assertNotNull(readedPatient);
    }

    @Test
    public void getPatientWithDAO() {
        Patient readedPatient = patientService.getPatientWithDAO(5);
        assertNotNull(readedPatient);
    }

    @Test
    public void updatePatient() {
        Patient patient = patientService.getPatient(5);
        System.out.println(patient);
        patient.setFirstName("Ian Alexis");
        patient.setLastName("Vaernet");
        patient.setDNI(32457412);
        patient.getAddress().setStreet("Av. Alvear");
        patient.getAddress().setNumber(830);
        Patient updatedPatient = patientService.updatePatient(patient.getId(), patient);
        assertEquals("Ian Alexis", updatedPatient.getFirstName());
        assertEquals("Vaernet", updatedPatient.getLastName());
        assertEquals(32457412, updatedPatient.getDNI());
        assertEquals("Av. Alvear", updatedPatient.getAddress().getStreet());
        assertEquals(830, updatedPatient.getAddress().getNumber());
    }

    @Test
    public void updatePatientWithDAO() throws SQLException {
        Patient patient = patientService.getPatient(5);
        patient.setFirstName("Juan");
        patient.setLastName("Perez");
        patient.setDNI(26261834);
        patient.getAddress().setStreet("25 de Mayo");
        patient.getAddress().setNumber(320);
        Patient updatedPatient = patientService.updatePatientWithDAO(patient.getId(), patient);
        assertEquals("Juan", updatedPatient.getFirstName());
        assertEquals("Perez", updatedPatient.getLastName());
        assertEquals(26261834, updatedPatient.getDNI());
        assertEquals("25 de Mayo", updatedPatient.getAddress().getStreet());
        assertEquals(320, updatedPatient.getAddress().getNumber());
    }

    @Test
    public void deletePatient() {
        patientService.deletePatient(6);
        assertNull(patientService.getPatient(6));
    }

    @Test
    public void deletePatientWithDAO() throws SQLException {
        patientService.deletePatientWithDAO(7);
        assertNull(patientService.getPatient(7));
    }

    @Test
    public void listPatients() throws SQLException {
        List<Patient> patients = patientService.listPatients();
        assertTrue(patients.size() > 0);
    }

    @Test
    public void listPatientsWithDAO() throws SQLException {
        List<Patient> patients = patientService.listPatientsWithDAO();
        assertTrue(patients.size() > 0);
    }
}
