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
    private JacksonTester<Application> json;

    @Test
    void shouldCreateValidJson() throws IOException{
        Application application =  new Application(0,"Software Developer");

        assertThat(json.write(application)).isStrictlyEqualToJson("app.json");
        

    }
}
