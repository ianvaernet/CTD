package com.example.TFI.Services;

import com.example.TFI.Models.Role;
import com.example.TFI.Models.User;
import com.example.TFI.Persistence.IUserDAO;
import com.example.TFI.Persistence.IUserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IUserDAO userDAO;

    public User login(String username, String password) {
        User user = this.userRepository.getByUsername(username);
        if (user != null && user.getPassword().equals(password)) return user;
        return null;
    }

    public User loginWithDAO(String username, String password) {
        User user = this.userDAO.getByUsername(username);
        if (user != null && user.getPassword().equals(password)) return user;
        return null;
    }

    public boolean changePassword(String username, String password, String newPassword) {
        User user = login(username, password);
        if (user == null) return false;
        user.setPassword(newPassword);
        userRepository.save(user);
        return true;
    }

    public boolean changePasswordWithDAO(String username, String password, String newPassword) {
        User user = loginWithDAO(username, password);
        if (user != null) return this.userDAO.changePassword(user.getId(), newPassword);
        return false;
    }

    public String getJWTToken(String username, Role role) {
        String secretKey = "$SG5%sgspH7ddmG38pr5vZZ+yx4mS*";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_"+role.toString());

        String token = Jwts
                .builder()
                .setIssuer("BackendTFI")
                .setSubject(username)
                .claim("authorities", grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 3600 * 1000))
                .signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();

        return "Bearer " + token;
    }
}
