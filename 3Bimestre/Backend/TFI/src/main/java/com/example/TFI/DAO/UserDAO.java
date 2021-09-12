package com.example.TFI.DAO;

import com.example.TFI.Models.User;

public interface UserDAO extends DAO<User> {

    public User getByUsername(String search);
}
