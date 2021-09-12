package com.company;

import com.company.DAO.DentistDAOH2;
import com.company.Models.Dentist;
import com.company.Services.DentistService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        DentistService dentistService = new DentistService(new DentistDAOH2());
        Dentist dentist = new Dentist(6272, "Juan", "Perez");
        Dentist dentist2 = new Dentist(9815, "Ana", "Sanchez");
        dentistService.createDentist(dentist);
        dentistService.createDentist(dentist2);
        dentistService.listDentists();
        dentistService.deleteDentist(dentist2.getId());
        dentist.setLicenseNumber(1234);
        dentist.setFirstName("Pedro");
        dentist.setLastName("Martinez");
        dentistService.updateDentist(dentist.getId(), dentist);
        dentistService.getDentist(dentist.getId());
    }
}
