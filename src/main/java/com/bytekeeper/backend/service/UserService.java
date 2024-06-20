package com.bytekeeper.backend.service;

import com.bytekeeper.backend.model.Product;
import com.bytekeeper.backend.model.User;
import com.bytekeeper.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repository;
    private PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }
    public Optional<User> getUserById(Long id) {
        return repository.findById(id);
    }

//    public Optional<User> getUserByToken(String token) {
//        var users = getAllUsers();
//        for (var u : users) {
//            if (u.getToken().equals(token)) {
//                return Optional.of(u);
//            }
//        }
//        return Optional.empty();
//    }

    public void addUser(User user) {
        var users = getAllUsers();
        for (var u : users) {
            if (u.getMail().equals(user.getMail())) {
                return;
            }
        }
        user.setPassword(encoder.encode(user.getPassword()));
        repository.save(user);
    }

    public Optional<User> getUserByMail(String mail) {
        var users = getAllUsers();
        for (var u : users) {
            if (u.getMail().equals(mail)) {
                return Optional.of(u);
            }
        }
        return Optional.empty();
    }

    public boolean checkPassword(User user, String password) {
        return encoder.matches(password, user.getPassword());
    }

}
