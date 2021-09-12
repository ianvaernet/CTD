package com.example.TFI.Services;

import com.example.TFI.DAO.DAO;
import com.example.TFI.Models.Dentist;
import com.example.TFI.Models.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DentistService {
    private final Connection databaseConnection;
    private final DAO<Dentist> DAO;
    private final DAO<User> userDAO;

    public DentistService(Connection databaseConnection, DAO<Dentist> DAO, DAO<User> userDAO) {
        this.databaseConnection = databaseConnection;
        this.DAO = DAO;
        this.userDAO = userDAO;
    }

    public Dentist createDentist(Dentist dentist) throws SQLException {
        try {
            databaseConnection.setAutoCommit(false);
            int id = userDAO.create(dentist);
            if (id == 0) return null;
            dentist.setId(id);
            id = DAO.create(dentist);
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

    public Dentist updateDentist(int id, Dentist dentist) throws SQLException {
        try {
            databaseConnection.setAutoCommit(false);
            User user = userDAO.update(id, dentist);
            if (user == null) return null;
            Dentist updatedDentist = DAO.update(id, dentist);
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

    public boolean deleteDentist(int id) throws SQLException {
        try {
            databaseConnection.setAutoCommit(false);
            boolean deleted = DAO.delete(id);
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
        return DAO.get(id);
    }

    public List<Dentist> listDentists() {
        return DAO.list();
    }
}
