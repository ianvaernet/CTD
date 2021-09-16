package com.example.TFI.Persistence;

import com.example.TFI.Models.Dentist;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class DentistDAOJDBC implements IDAO<Dentist> {
    private static final Logger logger = Logger.getLogger(DentistDAOJDBC.class);
    @Autowired
    private Connection dbConnection;

    public DentistDAOJDBC() {
    }

    @Override
    public int create(Dentist newDentist) {
        logger.debug("Creando odontólogo...");
        int id = 0;
        try {
            PreparedStatement statement = dbConnection.prepareStatement("INSERT INTO DENTISTS (id, license_number) VALUES (?, ?);", Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, newDentist.getId());
            statement.setInt(2, newDentist.getLicenseNumber());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
                logger.debug("Odontólogo creado con éxito: ID=" + id);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public Dentist update(int id, Dentist updatedDentist) {
        logger.debug("Actualizando odontólogo...");
        try {
            PreparedStatement statement = dbConnection.prepareStatement("UPDATE DENTISTS SET license_number = ? WHERE id = ?;");
            statement.setInt(1, updatedDentist.getLicenseNumber());
            statement.setInt(2, id);
            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                logger.debug("Odontólogo actualizado con éxito: " + updatedDentist);
                return updatedDentist;
            } else {
                logger.warn("No existe ningún odontólogo con el ID proporcionado");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        logger.debug("Eliminando odontólogo...");
        try {
            PreparedStatement statement = dbConnection.prepareStatement("DELETE FROM DENTISTS WHERE id = ?;");
            statement.setInt(1, id);
            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                logger.debug("Odontólogo eliminado con éxito: ID=" + id);
                return true;
            } else {
                logger.warn("No existe ningún odontólogo con el ID proporcionado");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Dentist get(int id) {
        logger.debug("Obteniendo odontólogo...");
        Dentist dentist = null;
        try {
            PreparedStatement statement = dbConnection.prepareStatement("SELECT * FROM DENTISTS INNER JOIN USERS ON DENTISTS.id = USERS.id WHERE DENTISTS.id = ?;");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                dentist = new Dentist(result.getString("username"), result.getString("password"), result.getInt("license_number"), result.getString("first_name"), result.getString("last_name"));
                dentist.setId(result.getInt("id"));
                logger.debug(dentist);
            } else {
                logger.warn("No existe ningún odontólogo con el ID proporcionado");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return dentist;
    }

    @Override
    public List<Dentist> list() {
        logger.debug("Listando odontólogos...");
        List<Dentist> dentists = null;
        try {
            Statement statement = dbConnection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM DENTISTS INNER JOIN USERS ON DENTISTS.id = USERS.id;");
            dentists = new ArrayList<>();
            while (result.next()) {
                Dentist dentist = new Dentist(result.getString("username"), result.getString("password"), result.getInt("license_number"), result.getString("first_name"), result.getString("last_name"));
                dentist.setId(result.getInt("id"));
                dentists.add(dentist);
                logger.debug(dentist);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return dentists;
    }
}
