package TFI.Tests;

import TFI.DAO.DentistDAOJDBC;
import TFI.DAO.H2Database;
import TFI.Models.Dentist;
import TFI.Services.DentistService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DentistServiceTest {
    private final DentistService dentistService = new DentistService(new DentistDAOJDBC(H2Database.getInMemoryConnection()));

    @Test
    public void createDentist() {
        Dentist dentist = new Dentist(3785, "Ian", "Vaernet");
        dentistService.createDentist(dentist);
        assertTrue(dentist.getId() > 0);
    }

    @Test
    public void getDentist() {
        Dentist dentist = new Dentist(3785, "Ian", "Vaernet");
        dentistService.createDentist(dentist);
        Dentist dentistFromDB = dentistService.getDentist(dentist.getId());
        assertNotNull(dentistFromDB);
    }

    @Test
    public void updateDentist() {
        Dentist dentist = new Dentist(3785, "Ian", "Vaernet");
        dentistService.createDentist(dentist);
        dentist.setLicenseNumber(1234);
        dentist.setFirstName("Ian Alexis");
        dentist.setLastName("Vaernet Pochon");
        Dentist updatedDentist = dentistService.updateDentist(dentist.getId(), dentist);
        assertEquals(1234, updatedDentist.getLicenseNumber());
        assertEquals("Ian Alexis", updatedDentist.getFirstName());
        assertEquals("Vaernet Pochon", updatedDentist.getLastName());
    }

    @Test
    public void deleteDentist() {
        Dentist dentist = new Dentist(3785, "Ian", "Vaernet");
        dentistService.createDentist(dentist);
        dentistService.deleteDentist(dentist.getId());
        assertNull(dentistService.getDentist(dentist.getId()));
    }

    @Test
    public void listDentists() {
        Dentist dentist = new Dentist(3785, "Ian", "Vaernet");
        dentistService.createDentist(dentist);
        List<Dentist> dentists = dentistService.listDentists();
        assertTrue(dentists.size() > 0);
    }
}
