package com.backend.backend.service;

import com.backend.backend.entity.User;
import com.backend.backend.exception.UserNotFoundException;
import com.backend.backend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User addUSer(User user) {
        user.setFName(user.getFName());
        user.setLName(user.getLName());
        user.setGender(user.getGender());
        user.setEmail(user.getEmail());
        user.setPassword(user.getPassword());
        user.setEnabled(user.isEnabled());
        return userRepo.save(user);
    }

    public List<User> findAllUsers() {
        return userRepo.findAll();
    }

    public User updateUser(User user) {
        return userRepo.save(user);
    }

    public void deleteUser(Long id) {
        userRepo.deleteUserById(id);
    }

    public User findUserById(Long id) {
        return userRepo.findUserById(id).orElseThrow(() -> new UserNotFoundException("user by id" + id + "not found"));
    }

    public User findUserByfName(String fName) {
        return userRepo.findUserByfNameStartingWithIgnoreCase(fName).orElseThrow(() -> new UserNotFoundException("user by name" + fName + "not found"));
    }

}
