package com.backend.backend.service;

import com.backend.backend.entity.User;
import com.backend.backend.exception.UserNotFoundException;
import com.backend.backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteUserById(id);
    }

    public User findUserById(Long id) {
        return userRepository.findUserById(id).orElseThrow(() -> new UserNotFoundException("user by id" + id + "not found"));
    }


}
