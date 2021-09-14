package com.example.TFI.Services;

import com.example.TFI.Models.Patient;

import java.sql.SQLException;
import java.util.List;

public interface IPatientService {
    public Patient createPatient(Patient patient) throws SQLException;
    public Patient updatePatient(int id, Patient patient) throws SQLException;
    public boolean deletePatient(int id) throws SQLException;
    public Patient getPatient(int id);
    public List<Patient> listPatients();
}
