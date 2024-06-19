package com.bytekeeper.backend.service;

import com.bytekeeper.backend.model.User;
import com.bytekeeper.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public Optional<User> getUserByToken(String token) {
        var users = getAllUsers();
        for (var u : users) {
            if (u.getToken().equals(token)) {
                return Optional.of(u);
            }
        }
        return Optional.empty();
    }

    public void addUser(User user) {
        var users = getAllUsers();
        for (var u : users) {
            if (u.getToken().equals(user.getToken())) {
                return;
            }
        }
        repository.save(user);
    }

}
