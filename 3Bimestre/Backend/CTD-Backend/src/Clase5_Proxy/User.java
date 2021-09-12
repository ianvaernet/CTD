package Clase5_Proxy;

public class User {
    private UserType userType;
    private String username;

    public User(UserType userType, String username) {
        this.userType = userType;
        this.username = username;
    }

    public UserType getUserType() {
        return userType;
    }
}
