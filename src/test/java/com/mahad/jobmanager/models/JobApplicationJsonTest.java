package com.mahad.jobmanager.models;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

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
        JobApplication application = new JobApplication(0, 0, "Software Developer", JobType.FULL_TIME);

        assertThat(json.write(application)).isStrictlyEqualToJson("expectedApplication.json");

    }
}
