package TFI.Services;

import TFI.DAO.DAO;
import TFI.Models.Patient;

import java.util.List;

public class PatientService extends UserService {
    private DAO<Patient> DAO;

    public PatientService(DAO<Patient> DAO) {
        this.DAO = DAO;
    }

    public Patient createPatient(Patient patient) {
        int id = DAO.create(patient);
        patient.setId(id);
        return patient;
    }

    public Patient updatePatient(int id, Patient patient) {
        return DAO.update(id, patient);
    }

    public void deletePatient(int id) {
        DAO.delete(id);
    }

    public Patient getPatient(int id) {
        return DAO.get(id);
    }

    public List<Patient> listPatients() {
        return DAO.list();
    }
}
