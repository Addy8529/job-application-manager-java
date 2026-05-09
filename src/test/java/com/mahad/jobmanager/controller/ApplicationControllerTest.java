package com.mahad.jobmanager.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void shouldReturnValidApplication() throws Exception{

        mvc.perform(get("/app/1"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.title").value("SE"));
    }

    @Test
    void shouldNotReturnApplicationIfItDoesNotExist() throws Exception{
        mvc.perform(get("/app/1000"))
        .andExpect(status().isNotFound());
    }

    @Test
    void shouldCreateApplication() throws Exception{
        String application = """
                {
                "id": null,
                "title": "Java Developer"
                }
                """;
        mvc.perform(post("/app").contentType(MediaType.APPLICATION_JSON).content(application))
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(header().exists("location"))
        .andExpect(jsonPath("$.title").value("Java Developer"));
    }

    @Test
    void shouldNotCreateApplicationWithInvalidTitle() throws Exception{
        String app1 = """
                {
                "id": null,
                "title": null
                }
                """;
        String app2 = """
                {
                "id": null,
                "title": ""
                }
                """;
        String app3 = """
                {
                "id": null,
                "title": " "
                }
                """;
        mvc.perform(post("/app").contentType(MediaType.APPLICATION_JSON).content(app1))
        .andExpect(status().isBadRequest());
        mvc.perform(post("/app").contentType(MediaType.APPLICATION_JSON).content(app2))
        .andExpect(status().isBadRequest());
        mvc.perform(post("/app").contentType(MediaType.APPLICATION_JSON).content(app3))
        .andExpect(status().isBadRequest());
    }





}
