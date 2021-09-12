package TFI.DAO;

import TFI.Models.Dentist;
import org.apache.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DentistDAOJDBC implements DAO<Dentist> {
    private static final Logger logger = Logger.getLogger(DentistDAOJDBC.class);
    private final Connection dbConnection;

    public DentistDAOJDBC() {
        this.dbConnection = H2Database.getConnection();
    }

    public DentistDAOJDBC(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public int create(Dentist newDentist) {
        int id = 0;
        try {
            PreparedStatement statement = dbConnection.prepareStatement("INSERT INTO DENTIST (license_number, first_name, last_name) VALUES (?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, newDentist.getLicenseNumber());
            statement.setString(2, newDentist.getFirstName());
            statement.setString(3, newDentist.getLastName());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public Dentist update(int id, Dentist updatedDentist) {
        try {
            PreparedStatement statement = dbConnection.prepareStatement("UPDATE DENTIST SET license_number = ?, first_name = ?, last_name = ? WHERE ID = ?;");
            statement.setInt(1, updatedDentist.getLicenseNumber());
            statement.setString(2, updatedDentist.getFirstName());
            statement.setString(3, updatedDentist.getLastName());
            statement.setInt(4, id);
            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
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
    public void delete(int id) {
        try {
            PreparedStatement statement = dbConnection.prepareStatement("DELETE FROM DENTIST WHERE ID = ?;");
            statement.setInt(1, id);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                logger.warn("No existe ningún odontólogo con el ID proporcionado");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public Dentist get(int id) {
        Dentist dentist = null;
        try {
            PreparedStatement statement = dbConnection.prepareStatement("SELECT * FROM DENTIST WHERE ID = ?;");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                dentist = new Dentist(result.getInt("license_number"), result.getString("first_name"), result.getString("last_name"));
                dentist.setId(result.getInt("id"));
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
        List<Dentist> dentists = new ArrayList<>();
        try {
            Statement statement = dbConnection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM DENTIST;");
            while (result.next()) {
                Dentist dentist = new Dentist(result.getInt("license_number"), result.getString("first_name"), result.getString("last_name"));
                dentist.setId(result.getInt("id"));
                dentists.add(dentist);
            }
        } catch (SQLException e) {
            logger.error("Error al listar la table DENTIST");
            e.printStackTrace();
        }
        return dentists;
    }
}
