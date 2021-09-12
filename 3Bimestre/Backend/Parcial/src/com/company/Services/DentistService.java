package com.company.Services;

import com.company.DAO.IDAO;
import com.company.Models.Dentist;

import java.util.List;

public class DentistService {
    private final IDAO<Dentist> DAO;

    public DentistService(IDAO<Dentist> DAO) {
        this.DAO = DAO;
    }

    public Dentist createDentist(Dentist dentist) {
        int id = DAO.create(dentist);
        dentist.setId(id);
        return dentist;
    }

    public Dentist updateDentist(int id, Dentist dentist) {
        return DAO.update(id, dentist);
    }

    public void deleteDentist(int id) {
        DAO.delete(id);
    }

    public Dentist getDentist(int id) {
        return DAO.get(id);
    }

    public List<Dentist> listDentists() {
        return DAO.list();
    }
}
