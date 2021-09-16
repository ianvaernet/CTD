package com.example.TFI;

import com.example.TFI.Models.Dentist;
import com.example.TFI.Models.Role;
import com.example.TFI.Models.User;
import com.example.TFI.Services.DentistService;
import com.example.TFI.Services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private DentistService dentistService;

    @Test
    public void login() {
        Dentist dentist = new Dentist("test_user_1", "hola1234", 3785, "Ian", "Vaernet");
        dentistService.createDentist(dentist);
        User user = userService.login("test_user_1", "hola1234");
        assertNotNull(user);
        assertEquals(Role.DENTIST, user.getRole());
    }
    @Test
    public void loginWithDAO() throws SQLException {
        Dentist dentist = new Dentist("test_user_dao_1", "hola1234", 3785, "Ian", "Vaernet");
        dentistService.createDentistWithDAO(dentist);
        User user = userService.loginWithDAO("test_user_dao_1", "hola1234");
        assertNotNull(user);
        assertEquals(Role.DENTIST, user.getRole());
    }

    @Test
    public void loginWithInexistingUsername() {
        Dentist dentist = new Dentist("test_user_2", "hola1234", 3785, "Ian", "Vaernet");
        dentistService.createDentist(dentist);
        User userWithWrongUsername = userService.login("fake_user", "hola1234");
        assertNull(userWithWrongUsername);
    }

    @Test
    public void loginWithWrongPassword() {
        Dentist dentist = new Dentist("test_user_3", "hola1234", 3785, "Ian", "Vaernet");
        dentistService.createDentist(dentist);
        User userWithWrongPassword = userService.login("test_user_3", "asdqwe");
        assertNull(userWithWrongPassword);
    }

    @Test
    public void changePassword() {
        Dentist dentist = new Dentist("test_user_4", "hola1234", 3785, "Ian", "Vaernet");
        dentistService.createDentist(dentist);
        boolean passwordChanged = userService.changePassword("test_user_4", "hola1234", "chau9876");
        assertTrue(passwordChanged);
        User userWithOldPassword = userService.login("test_user_4", "hola1234");
        assertNull(userWithOldPassword);
        User user = userService.login("test_user_4", "chau9876");
        assertNotNull(user);
    }
    @Test
    public void changePasswordWithDAO() throws SQLException {
        Dentist dentist = new Dentist("test_user_dao_4", "hola1234", 3785, "Ian", "Vaernet");
        dentistService.createDentistWithDAO(dentist);
        boolean passwordChanged = userService.changePasswordWithDAO("test_user_dao_4", "hola1234", "chau9876");
        assertTrue(passwordChanged);
        User userWithOldPassword = userService.loginWithDAO("test_user_dao_4", "hola1234");
        assertNull(userWithOldPassword);
        User user = userService.loginWithDAO("test_user_dao_4", "chau9876");
        assertNotNull(user);
    }

    @Test
    public void changePasswordWithWrongOldPassword() {
        Dentist dentist = new Dentist("test_user_5", "hola1234", 3785, "Ian", "Vaernet");
        dentistService.createDentist(dentist);
        boolean passwordChanged = userService.changePassword("test_user_5", "wrong_password", "chau9876");
        assertFalse(passwordChanged);
        User userWithOldPassword = userService.login("test_user_5", "chau9876");
        assertNull(userWithOldPassword);
    }
}
