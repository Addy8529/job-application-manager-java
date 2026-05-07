package com.mahad.jobmanager.models;

import java.time.LocalDate;

record Application(
    int id,
    int companyId,
    String roleTitle,
    JobType jobType,
    ApplicationStatus status,
    LocalDate dateApplied,
    LocalDate followUpDate,
    String notes
) {}
