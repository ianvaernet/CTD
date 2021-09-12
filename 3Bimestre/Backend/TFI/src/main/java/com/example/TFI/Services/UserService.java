package com.example.TFI.Services;

import com.example.TFI.DAO.UserDAO;
import com.example.TFI.Models.User;

public class UserService {
    private final UserDAO DAO;

    public UserService(UserDAO DAO) {
        this.DAO = DAO;
    }

    public User login(String username, String password) {
        User user = this.DAO.getByUsername(username);
        if (user != null && user.getPassword().equals(password)) return user;
        return null;
    }

    public boolean changePassword(String username, String password, String newPassword) {
        User user = login(username, password);
        if (user != null) {
            user.setPassword(newPassword);
            user = this.DAO.update(user.getId(), user);
            return user != null;
        }
        return false;
    }
}
