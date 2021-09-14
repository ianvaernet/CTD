package com.example.TFI.Persistence;

import com.example.TFI.Models.User;

public interface IUserDAO extends IDAO<User> {
    public User getByUsername(String username);

    public boolean changePassword(int id, String newPassword);
}
