package com.example.TFI.Services;

import com.example.TFI.Models.User;
import com.example.TFI.Persistence.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;
//    private IUserDAO userDAO;

    public User login(String username, String password) {
        User user = this.userRepository.getByUsername(username);
        if (user != null && user.getPassword().equals(password)) return user;
        return null;
    }

//    public User loginWithDAO(String username, String password) {
//        User user = this.userDAO.getByUsername(username);
//        if (user != null && user.getPassword().equals(password)) return user;
//        return null;
//    }

    public boolean changePassword(String username, String password, String newPassword) {
        User user = login(username, password);
        if (user == null) return false;
        user.setPassword(newPassword);
        userRepository.save(user);
        return true;
    }

//    public boolean changePasswordWithDAO(String username, String password, String newPassword) {
//        User user = login(username, password);
//        if (user != null) return this.userDAO.changePassword(user.getId(), newPassword);
//        return false;
//    }
}
