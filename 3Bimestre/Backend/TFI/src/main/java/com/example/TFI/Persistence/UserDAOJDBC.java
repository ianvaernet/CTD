package com.example.TFI.Persistence;

import com.example.TFI.Models.Role;
import com.example.TFI.Models.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAOJDBC implements IUserDAO {
    private static final Logger logger = Logger.getLogger(UserDAOJDBC.class);
    @Autowired
    private Connection dbConnection;

    public UserDAOJDBC() {
    }

    @Override
    public int create(User newUser) {
        logger.debug("Creando usuario...");
        int id = 0;
        try {
            PreparedStatement statement = dbConnection.prepareStatement("INSERT INTO USERS (role, username, password, first_name, last_name) VALUES (?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, newUser.getRole().ordinal());
            statement.setString(2, newUser.getUsername());
            statement.setString(3, newUser.getPassword());
            statement.setString(4, newUser.getFirstName());
            statement.setString(5, newUser.getLastName());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
                logger.debug("Usuario creado con éxito: ID=" + id);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public User update(int id, User updatedUser) {
        logger.debug("Actualizando usuario...");
        try {
            PreparedStatement statement = dbConnection.prepareStatement("UPDATE USERS SET username = ?, first_name = ?, last_name = ? WHERE ID = ?;");
            statement.setString(1, updatedUser.getUsername());
            statement.setString(2, updatedUser.getFirstName());
            statement.setString(3, updatedUser.getLastName());
            statement.setInt(4, id);
            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                logger.debug("Usuario actualizado con éxito: " + updatedUser);
                return updatedUser;
            } else {
                System.out.println("No existe ningún usuario con el ID proporcionado");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean changePassword(int id, String newPassword) {
        logger.debug("Cambiando contraseña...");
        try {
            PreparedStatement statement = dbConnection.prepareStatement("UPDATE USERS SET password = ? WHERE ID = ?;");
            statement.setString(1, newPassword);
            statement.setInt(2, id);
            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                logger.debug("Contraseña actualizada con éxito para el usuario con ID = " + id);
                return true;
            } else {
                System.out.println("No existe ningún usuario con el ID proporcionado");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        logger.debug("Eliminando usuario...");
        try {
            PreparedStatement statement = dbConnection.prepareStatement("DELETE FROM USERS WHERE ID = ?;");
            statement.setInt(1, id);
            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                logger.debug("Usuario eliminado con éxito: ID=" + id);
                return true;
            } else {
                logger.warn("No existe ningún usuario con el ID proporcionado");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User get(int id) {
        logger.debug("Obteniendo usuario...");
        User user = null;
        try {
            PreparedStatement statement = dbConnection.prepareStatement("SELECT * FROM USERS WHERE id = ?;");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                user = new User(Role.values()[result.getInt("role")], result.getString("username"), result.getString("password"), result.getString("first_name"), result.getString("last_name"));
                logger.debug(user);
            } else {
                logger.warn("No existe ningún usuario con el ID proporcionado");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User getByUsername(String username) {
        logger.debug("Obteniendo usuario según username...");
        User user = null;
        try {
            PreparedStatement statement = dbConnection.prepareStatement("SELECT * FROM USERS WHERE username = ?;");
            statement.setString(1, username);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                user = new User(Role.values()[result.getInt("role")], username, result.getString("password"), result.getString("first_name"), result.getString("last_name"));
                user.setId(result.getInt("id"));
                logger.debug(user);
            } else {
                logger.warn("No existe ningún usuario con el username proporcionado");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> list() {
        logger.debug("Listando usuarios...");
        List<User> users = new ArrayList<>();
        try {
            Statement statement = dbConnection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM USERS;");
            while (result.next()) {
                User user = new User(Role.values()[result.getInt("role")], result.getString("username"), result.getString("password"), result.getString("first_name"), result.getString("last_name"));
                user.setId(result.getInt("id"));
                users.add(user);
                logger.debug(user);
            }
        } catch (SQLException e) {
            logger.error("Error al listar usuarios");
            e.printStackTrace();
        }
        return users;
    }
}
