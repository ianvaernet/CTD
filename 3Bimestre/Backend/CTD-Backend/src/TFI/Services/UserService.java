package TFI.Services;

import TFI.DAO.DAO;
import TFI.Models.User;

public abstract class UserService {
    private DAO<User> DAO;

//    public boolean login(String username, String password) {
//        User user = this.DAO.search("username", username);
//        return user != null && user.getPassword().equals(password);
//    }
//
//    public void changePassword(String username, String password, String newPassword) {
//
//    }
}
