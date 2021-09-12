package TFI.Services;

import TFI.DAO.DAO;
import TFI.Models.Dentist;
import java.util.List;

public class DentistService {
    private final DAO<Dentist> DAO;

    public DentistService(DAO<Dentist> DAO) {
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
