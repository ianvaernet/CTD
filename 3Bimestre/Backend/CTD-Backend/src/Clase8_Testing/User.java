package Clase8_Testing;

import java.time.LocalDate;
import java.time.Period;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthdate;

    public User(String firstName, String lastName, String email, LocalDate birthdate) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setBirthdate(birthdate);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName.length() > 30) throw new RuntimeException("El nombre no puede tener más de 30 caracteres");
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName.length() > 30) throw new RuntimeException("El apellido no puede tener más de 30 caracteres");
        this.lastName = lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public boolean isAdult() {
        return Period.between(birthdate, LocalDate.now()).getYears() >= 18;
    }
}
