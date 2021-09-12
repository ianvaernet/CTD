package com.example.TFI;

import com.example.TFI.DAO.DentistDAOJDBC;
import com.example.TFI.DAO.H2Database;
import com.example.TFI.DAO.UserDAOJDBC;
import com.example.TFI.Models.Dentist;
import com.example.TFI.Models.Role;
import com.example.TFI.Models.User;
import com.example.TFI.Services.DentistService;
import com.example.TFI.Services.UserService;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {
    private final UserService userService = new UserService(new UserDAOJDBC(H2Database.getInMemoryConnection()));
    private final DentistService dentistService = new DentistService(H2Database.getInMemoryConnection(), new DentistDAOJDBC(H2Database.getInMemoryConnection()), new UserDAOJDBC(H2Database.getInMemoryConnection()));

    @Test
    public void login() throws SQLException {
        Dentist dentist = new Dentist("test_user_1", "hola1234",3785, "Ian", "Vaernet");
        dentistService.createDentist(dentist);
        User user = userService.login("test_user_1", "hola1234");
        assertNotNull(user);
        assertEquals(Role.DENTIST, user.getRole());
    }

    @Test
    public void loginWithInexistingUsername() throws SQLException {
        Dentist dentist = new Dentist("test_user_2", "hola1234",3785, "Ian", "Vaernet");
        dentistService.createDentist(dentist);
        User userWithInexistinUsername = userService.login("fake_user", "hola1234");
        assertNull(userWithInexistinUsername);
    }
    
    @Test
    public void loginWithWrongPassword() throws SQLException {
        Dentist dentist = new Dentist("test_user_3", "hola1234",3785, "Ian", "Vaernet");
        dentistService.createDentist(dentist);
        User userWithWrongPassword = userService.login("test_user_3", "asdqwe");
        assertNull(userWithWrongPassword);
    }

    @Test
    public void changePassword() throws SQLException {
        Dentist dentist = new Dentist("test_user_4", "hola1234",3785, "Ian", "Vaernet");
        dentistService.createDentist(dentist);
        boolean passwordChanged = userService.changePassword("test_user_4", "hola1234", "chau9876");
        assertTrue(passwordChanged);
        User userWithOldPassword = userService.login("test_user_4", "hola1234");
        assertNull(userWithOldPassword);
        User user = userService.login("test_user_4", "chau9876");
        assertNotNull(user);
    }

    @Test
    public void changePasswordWithWrongOldPassword() throws SQLException {
        Dentist dentist = new Dentist("test_user_5", "hola1234",3785, "Ian", "Vaernet");
        dentistService.createDentist(dentist);
        boolean passwordChanged = userService.changePassword("test_user_5", "wrong_password", "chau9876");
        assertFalse(passwordChanged);
        User userWithOldPassword = userService.login("test_user_5", "chau9876");
        assertNull(userWithOldPassword);
    }
}
