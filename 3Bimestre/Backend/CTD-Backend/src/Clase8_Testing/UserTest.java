package Clase8_Testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
//import org.junit.runner.RunWith;
//import org.junit.runners.Parametrized;


class UserTest {

    @Test
    public void createUser() {
        User user = new User("Ian", "Vaernet", "ianvaernet@gmail.com", LocalDate.of(1997, 11, 17));
        assertEquals("Ian", user.getFirstName(), "First name");
        assertEquals("Vaernet", user.getLastName(), "Last name");
        assertEquals("ianvaernet@gmail.com", user.getEmail(), "Email");
        assertEquals(LocalDate.of(1997, 11, 17), user.getBirthdate());
    }

    @Test
    public void getFullName() {
        User user = new User("Ian", "Vaernet", "ianvaernet@gmail.com", LocalDate.of(1997, 11, 17));
        assertEquals("Ian Vaernet", user.getFullName());
    }

    @Test
    public void isAdult() {
        User user = new User("Ian", "Vaernet", "ianvaernet@gmail.com", LocalDate.of(1997, 11, 17));
        assertTrue(user.isAdult());
    }

    @Test
    public void isNotAdult() {
        User user = new User("Ian", "Vaernet", "ianvaernet@gmail.com", LocalDate.of(2007, 11, 17));
        assertFalse(user.isAdult());
    }

    @Test
    public void firstNameMaxLengthExceeded() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> new User("Adswefgsdergnskfgsngsdrgvsdvsrsgsdg", "Vaernet", "ianvaernet@gmail.com", LocalDate.of(1997, 11, 17)));
        assertTrue(exception.getMessage().contains("30 caracteres"));
    }

    @Test
    public void lastNameMaxLengthExceeded() {
        User user = new User("Ian", "Vaernet", "ianvaernet@gmail.com", LocalDate.of(1997, 11, 17));
        RuntimeException exception = assertThrows(RuntimeException.class, () -> user.setLastName("Adswefgsdergnskfgsngsdrgvsdvsrsgsdg"));
        assertTrue(exception.getMessage().contains("30 caracteres"));
    }
}