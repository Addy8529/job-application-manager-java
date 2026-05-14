package com.mahad.jobmanager.models;

import jakarta.persistence.*;

@Entity
public class NewCompany {

    public NewCompany() {
    }

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String description;

    private String url;
    private Integer numberOfEmployees;

    public NewCompany(Integer id, String name, String description, String url, Integer numberOfEmployees) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
        this.numberOfEmployees = numberOfEmployees;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public Integer getNumberOfEmployees() {
        return numberOfEmployees;
    }







}



