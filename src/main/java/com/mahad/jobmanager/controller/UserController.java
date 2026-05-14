package com.mahad.jobmanager.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mahad.jobmanager.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository repository;

    @SuppressWarnings("null")
    @PostMapping
    private ResponseEntity<Void> createUser(){

        return ResponseEntity.created(URI.create("/user/0")).build();
    }

    


}
