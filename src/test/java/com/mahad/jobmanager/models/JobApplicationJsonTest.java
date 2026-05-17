package com.mahad.jobmanager.models;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

@JsonTest
public class JobApplicationJsonTest {

    @Autowired
    private JacksonTester<JobApplication> json;

    @Test
    void shouldCreateValidJson() throws IOException{
        JobApplication application =  new JobApplication(
            0,
            0,
            "Software Developer",
            JobType.FULL_TIME,
            ApplicationStatus.APPLIED,
            LocalDate.of(2026, 5, 7),
            LocalDate.of(2026, 5, 14),
            "No notes."
        );

        assertThat(json.write(application)).isStrictlyEqualToJson("expectedApplication.json");
        assertThat(json.write(application)).hasJsonPathNumberValue("@.id");
        assertThat(json.write(application)).extractingJsonPathStringValue("@.roleTitle")
                .isEqualTo("Software Developer");


    }
}
