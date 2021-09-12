package TFI.Tests;

import TFI.DAO.H2Database;
import TFI.DAO.PatientDAOJDBC;
import TFI.Models.Patient;
import TFI.Services.PatientService;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class PatientServiceTest {
    private PatientService patientService = new PatientService(new PatientDAOJDBC(H2Database.getInMemoryConnection()));

    @Test
    public void createPatient() {
        Patient patient = new Patient("ianvaernet", "hola1234", "Ian", "Vaernet");
        patientService.createPatient(patient);
        assertTrue(patient.getId() > 0);
    }

    @Test
    public void getPatient() {
        Patient patient = new Patient("ianvaernet", "hola1234", "Ian", "Vaernet");
        patientService.createPatient(patient);
        Patient patientFromDB = patientService.getPatient(patient.getId());
        assertNotNull(patientFromDB);
    }

    @Test
    public void updatePatient() {
        Patient patient = new Patient("ianvaernet", "hola1234", "Ian", "Vaernet");
        patientService.createPatient(patient);
        patient.setFirstName("Ian Alexis");
        Patient updatedPatient = patientService.updatePatient(patient.getId(), patient);
        assertEquals("Ian Alexis", updatedPatient.getFirstName());
    }

    @Test
    public void deletePatient() {
        Patient patient = new Patient("ianvaernet", "hola1234", "Ian", "Vaernet");
        patientService.createPatient(patient);
        patientService.deletePatient(patient.getId());
        assertNull(patientService.getPatient(patient.getId()));
    }

    @Test
    public void listPatients() {
        Patient patient = new Patient("ianvaernet", "hola1234", "Ian", "Vaernet");
        patientService.createPatient(patient);
        List<Patient> patients = patientService.listPatients();
        assertTrue(patients.size() > 0);
    }
}
