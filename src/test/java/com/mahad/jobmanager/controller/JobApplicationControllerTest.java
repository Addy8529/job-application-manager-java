package com.mahad.jobmanager.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mahad.jobmanager.repository.NewCompany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JobApplicationControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void shouldAddACompanyIntoRepository(){
        NewCompany company =  new NewCompany(0, "IBM", "test", "https://ibm.com", 10);
        ResponseEntity<Void> response = restTemplate.postForEntity("/company", company, Void.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);


    }

    @Test
    void shouldReturnACompany(){

        ResponseEntity<String> response = restTemplate.getForEntity("/company/0",String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);



    }
}
