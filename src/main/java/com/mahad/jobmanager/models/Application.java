package com.mahad.jobmanager.models;


import org.springframework.data.annotation.Id;
import io.micrometer.common.lang.NonNull;


public record Application(
    @Id Integer id,
    @NonNull String title
) {}
