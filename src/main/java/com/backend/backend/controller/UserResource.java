package com.backend.backend.controller;

import com.backend.backend.entity.User;
import com.backend.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserResource {
    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") Long id) {
        User user = userService.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.FOUND);
    }

    @GetMapping("/findBy/{fName}")
    public ResponseEntity<User> getByName(@PathVariable("fName") String fName) {
        User user = userService.findUserByfName(fName);
        return new ResponseEntity<>(user, HttpStatus.FOUND);
    }


    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User newUser = userService.addUSer(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User updatedUser = userService.updateUser(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}


//MY CONCEPTS

//    @DeleteMapping("/delete/{id}")
//    public void deleteById(@PathVariable("id") Long id){
//         userService.deleteUser(id);
//    }

//    @PutMapping("/updateUser/{id}")
//    public ResponseEntity<User> updateUser(@PathVariable("id") Long id){
//        User user = userService.updateUser(id);
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }