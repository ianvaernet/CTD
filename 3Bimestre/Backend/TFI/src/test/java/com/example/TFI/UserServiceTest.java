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
        User user = userService.login("admin", "admin");
        assertNotNull(user);
        assertEquals(Role.ADMIN, user.getRole());
    }
    @Test
    public void loginWithDAO() {
        User user = userService.loginWithDAO("admin", "admin");
        assertNotNull(user);
        assertEquals(Role.ADMIN, user.getRole());
    }

    @Test
    public void loginWithInexistingUsername() {
        User userWithWrongUsername = userService.login("wrong_user", "admin");
        assertNull(userWithWrongUsername);
    }

    @Test
    public void loginWithWrongPassword() {
        User userWithWrongPassword = userService.login("admin", "wrong_password");
        assertNull(userWithWrongPassword);
    }

    @Test
    public void changePassword() {
        boolean passwordChanged = userService.changePassword("paciente", "paciente", "new_password");
        assertTrue(passwordChanged);
        User userWithOldPassword = userService.login("paciente", "wrong_password");
        assertNull(userWithOldPassword);
        User user = userService.login("paciente", "new_password");
        assertNotNull(user);
    }
    @Test
    public void changePasswordWithDAO() {
        boolean passwordChanged = userService.changePasswordWithDAO("odontologo", "odontologo", "new_password");
        assertTrue(passwordChanged);
        User userWithOldPassword = userService.loginWithDAO("odontologo", "odontologo");
        assertNull(userWithOldPassword);
        User user = userService.loginWithDAO("odontologo", "new_password");
        assertNotNull(user);
    }

    @Test
    public void changePasswordWithWrongOldPassword() {
        boolean passwordChanged = userService.changePassword("paciente", "wrong_password", "new_password");
        assertFalse(passwordChanged);
        User userWithNewPassword = userService.login("paciente", "new_password");
        assertNull(userWithNewPassword);
    }
}
