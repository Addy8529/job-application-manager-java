package com.mahad.jobmanager.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mahad.jobmanager.models.*;
import com.mahad.jobmanager.repository.ApplicationRepository;

@RestController
@RequestMapping("/app")
public class ApplicationController {

    @Autowired
    private ApplicationRepository applicationRepository;

    public ApplicationController(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @GetMapping("/{id}")
    private ResponseEntity<Application> getApplicationById(@PathVariable Integer id){
        Optional<Application> application = applicationRepository.findById(id);

        if ( application.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(application.get());
    }
    //@GetMapping
    
    //@PutMapping("/{id}")
    //@DeleteMapping("/{id}")


}
