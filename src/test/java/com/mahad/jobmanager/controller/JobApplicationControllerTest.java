package com.mahad.jobmanager.controller;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JobApplicationControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void controllerReturnsValidCompany(){

        ResponseEntity<String> response = restTemplate.getForEntity("/app/companies/0", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    }
}
