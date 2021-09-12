package Tests;

import com.company.DAO.DentistDAOH2;
import com.company.DAO.H2Database;
import com.company.Models.Dentist;
import com.company.Services.DentistService;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class DentistServiceTest {
    private final DentistService dentistService = new DentistService(new DentistDAOH2(H2Database.getInMemoryConnection()));

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
        dentist.setFirstName("Ian Alexis");
        Dentist updatedDentist = dentistService.updateDentist(dentist.getId(), dentist);
        assertEquals("Ian Alexis", updatedDentist.getFirstName());
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
