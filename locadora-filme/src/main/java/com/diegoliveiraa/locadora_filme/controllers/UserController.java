package com.diegoliveiraa.locadora_filme.controllers;

import com.diegoliveiraa.locadora_filme.dtos.UserDTO;
import com.diegoliveiraa.locadora_filme.entitys.User;
import com.diegoliveiraa.locadora_filme.servives.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDTO data) {

        User newUser = this.userService.createUser(data);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PostMapping("/update-user")
    public ResponseEntity<User> updateUser(@RequestBody UserDTO data) {

        User updateUser = this.userService.updateUser(data);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    @RequestMapping
    public ResponseEntity<List<User>> searchAllUser() {

        List<User> allUsers = this.userService.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @RequestMapping("/search-user")
    public ResponseEntity<User> searchUser(@RequestBody String document, @RequestBody String name) {

        User searchUser = this.userService.findByDocumentOrName(document, name);
        return new ResponseEntity<>(searchUser, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<User> deleteUser(@RequestBody String id) {
        User deleteUser = this.userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
