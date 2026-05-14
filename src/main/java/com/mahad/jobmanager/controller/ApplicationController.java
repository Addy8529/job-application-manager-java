package com.mahad.jobmanager.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

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
    private ResponseEntity<Application> getApplicationById(@PathVariable("id") Integer id){
        Optional<Application> application = applicationRepository.findById(id);

        if ( application.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(application.get());
    }

    @GetMapping
    private ResponseEntity<Iterable<Application>> getAllApplications(){

        return ResponseEntity.ok(applicationRepository.findAll());
    }


    @PostMapping
    private ResponseEntity<Application> createApplication( @RequestBody @NonNull Application application){

        if (application.getTitle() == null || application.getTitle().strip().isBlank()) return ResponseEntity.badRequest().build();

        Application newApplication = applicationRepository.save(application);

        return ResponseEntity.created(URI.create("/app/" + newApplication.getId())).body(newApplication);
    }

    @PutMapping("/{id}")
    private ResponseEntity<Void> updateApplicationById(
        @PathVariable("id") @NonNull Integer id,
        @RequestBody Application updatedApplication){
            if ( applicationRepository.findById(id).isPresent()){
                applicationRepository.save(new Application(id, updatedApplication.getTitle()));
                return ResponseEntity.noContent().build();
            }else{
                return ResponseEntity.notFound().build();
            }


    }
    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteApplicationById(@PathVariable("id") @NonNull Integer id){
        applicationRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
