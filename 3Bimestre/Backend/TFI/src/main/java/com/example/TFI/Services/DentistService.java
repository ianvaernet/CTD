package com.example.TFI.Services;

import com.example.TFI.Models.Role;
import com.example.TFI.Persistence.IDentistRepository;
import com.example.TFI.Persistence.IDAO;
import com.example.TFI.Models.Dentist;
import com.example.TFI.Models.User;
import com.example.TFI.Persistence.IUserDAO;
import com.example.TFI.Persistence.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class DentistService {
//  ORM repositories:
    @Autowired
    private IDentistRepository dentistRepository;
    @Autowired
    private IUserRepository userRepository;
//  DAO with JDBC:
    @Autowired
    private Connection databaseConnection;
    @Autowired
    private IDAO<Dentist> dentistDAO;
    @Autowired
    private IUserDAO userDAO;

    @Transactional
    public Dentist createDentist(Dentist dentist) {
        dentist.setRole(Role.DENTIST);
        User user = userRepository.save(dentist);
        dentist = dentistRepository.save(dentist);
        return dentist;
    }

    public Dentist createDentistWithDAO(Dentist dentist) throws SQLException {
        try {
            databaseConnection.setAutoCommit(false);
            int id = userDAO.create(dentist);
            if (id == 0) return null;
            dentist.setId(id);
            id = dentistDAO.create(dentist);
            if (id == 0) {
                databaseConnection.rollback();
                return null;
            }
            databaseConnection.commit();
            return dentist;
        } finally {
            databaseConnection.setAutoCommit(true);
        }
    }

    @Transactional
    public Dentist updateDentist(int id, Dentist dentist) {
        dentist.setId(id);
        User user = userRepository.save(dentist);
        dentist = dentistRepository.save(dentist);
        return dentist;
    }

    public Dentist updateDentistWithDAO(int id, Dentist dentist) throws SQLException {
        try {
            databaseConnection.setAutoCommit(false);
            User user = userDAO.update(id, dentist);
            if (user == null) return null;
            Dentist updatedDentist = dentistDAO.update(id, dentist);
            if (updatedDentist == null) {
                databaseConnection.rollback();
                return null;
            }
            dentist.setId(id);
            databaseConnection.commit();
        } finally {
            databaseConnection.setAutoCommit(true);
        }
        return dentist;
    }

    public boolean deleteDentist(int id) {
        userRepository.deleteById(id);
        return true;
    }

    public boolean deleteDentistWithDAO(int id) throws SQLException {
        try {
            databaseConnection.setAutoCommit(false);
            boolean deleted = dentistDAO.delete(id);
            if (!deleted) return false;
            deleted = userDAO.delete(id);
            if (!deleted) {
                databaseConnection.rollback();
                return false;
            }
            databaseConnection.commit();
            return true;
        } finally {
            databaseConnection.setAutoCommit(true);
        }
    }

    public Dentist getDentist(int id) {
        Optional<Dentist> dentist = dentistRepository.findById(id);
        if (dentist.isEmpty()) return null;
        return dentist.get();
    }

    public Dentist getDentistWithDAO(int id) {
        return dentistDAO.get(id);
    }

    public List<Dentist> listDentists() {
        return dentistRepository.findAll();
    }

    public List<Dentist> listDentistsWithDAO() {
        return dentistDAO.list();
    }
}
