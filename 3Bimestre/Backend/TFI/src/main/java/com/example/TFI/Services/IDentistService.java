package com.example.TFI.Services;

import com.example.TFI.Models.Dentist;

import java.sql.SQLException;
import java.util.List;

public interface IDentistService {
    public Dentist createDentist(Dentist dentist) throws SQLException;
    public Dentist updateDentist(int id, Dentist dentist) throws SQLException;
    public boolean deleteDentist(int id) throws SQLException;
    public Dentist getDentist(int id);
    public List<Dentist> listDentists();
}
