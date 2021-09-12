package Clase4_ChainOfResponsability;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String role;

    public User(String firstName, String lastName, String email, String username, String password, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}
