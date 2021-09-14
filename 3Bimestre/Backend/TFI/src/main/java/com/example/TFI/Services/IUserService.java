package com.example.TFI.Services;

import com.example.TFI.Models.User;

public interface IUserService {
    public User login(String username, String password);
    public boolean changePassword(String username, String password, String newPassword);
}
