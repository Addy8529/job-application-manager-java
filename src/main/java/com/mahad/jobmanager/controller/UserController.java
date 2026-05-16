package com.mahad.jobmanager.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mahad.jobmanager.models.User;
import com.mahad.jobmanager.repository.UserRepository;

import io.micrometer.common.lang.NonNull;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository repository;

    @SuppressWarnings("null")
    @PostMapping
    private ResponseEntity<Void> createUser(@RequestBody User user){
        User createdUser = repository.save(user);
        return ResponseEntity.created(URI.create("/user/"+createdUser.getId())).build();
    }

    @GetMapping("/{id}")
    private ResponseEntity<User> getUser( @PathVariable("id") Integer id){

        Optional<User> user = repository.findById(id);

        if (user.isEmpty()){
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user.get());
        }





}
