package com.mahad.jobmanager.models;

import org.springframework.data.annotation.Id;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Entity;

@Entity
public record Application(
    @Id Integer id,
    @NonNull String title
) {}
