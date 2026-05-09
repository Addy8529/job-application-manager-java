package com.mahad.jobmanager.models;


import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public record Application(
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) Integer id,
    @NonNull String title
) {}
