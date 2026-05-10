package com.mahad.jobmanager.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CompanyControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void shouldReturnAValidCompanyIfCompanyExists() throws Exception{

        mvc.perform(get("/company/0"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").exists())
        .andExpect(jsonPath("$.name").exists())
        .andExpect(jsonPath("$.url").exists())
        .andExpect(jsonPath("$.description").exists())
        .andExpect(jsonPath("$.numberOfEmployees").exists());
    }

    @Test
    void shouldNotReturnACompanyIfCompanyDoesNotExists() throws Exception{

        mvc.perform(get("/company/1"))
        .andExpect(status().isNotFound());
    }

    @Test
    void shouldAcceptAValidCompany() throws Exception{
        String company = """
                {
                    "id": null,
                    "name": "APPLE",
                    "url": "https://apple.com",
                    "description": "test",
                    "numberOfEmployees": 10
                }
                """;
        mvc.perform(post("/company").contentType(MediaType.APPLICATION_JSON).content(company))
        .andExpect(status().isCreated())
        .andExpect(header().exists("location"))
        .andExpect(header().string("location","/company/1"));

        mvc.perform(get("/company/1"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value("APPLE"))
            .andExpect(jsonPath("$.url").value("https://apple.com"))
            .andExpect(jsonPath("$.description").value("test"))
            .andExpect(jsonPath("$.numberOfEmployees").value(10));
    }

    @Test
    void shouldUpdateACompanyIfItExists() throws Exception{
        String newcompany = """
                {
                    "id": null,
                    "name": "APPLE",
                    "url": "https://apple.com",
                    "description": "test",
                    "numberOfEmployees": 10
                }
                """;

        mvc.perform(put("/company/0").contentType(MediaType.APPLICATION_JSON).content(newcompany))
        .andExpect(status().isNoContent());

        mvc.perform(get("/company/0"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(0))
            .andExpect(jsonPath("$.name").value("APPLE"))
            .andExpect(jsonPath("$.url").value("https://apple.com"))
            .andExpect(jsonPath("$.description").value("test"))
            .andExpect(jsonPath("$.numberOfEmployees").value(10));
    }

    @Test
    void shouldNotUpdateACompanyIfItDoesNotExists() throws Exception{
        String newcompany = """
                {
                    "id": null,
                    "name": "APPLE",
                    "url": "https://apple.com",
                    "description": "test",
                    "numberOfEmployees": 10
                }
                """;

        mvc.perform(put("/company/2").contentType(MediaType.APPLICATION_JSON).content(newcompany))
        .andExpect(status().isNotFound());
    }

    @Test
    void shouldDeleteACompanyIfItExists() throws Exception{
        String company = """
                {
                    "id": null,
                    "name": "APPLE",
                    "url": "https://apple.com",
                    "description": "test",
                    "numberOfEmployees": 10
                }
                """;

        mvc.perform(post("/company").contentType(MediaType.APPLICATION_JSON).content(company))
        .andExpect(status().isCreated())
        .andExpect(header().exists("location"));

        mvc.perform(delete("/company/1")).andExpect(status().isNoContent());
        mvc.perform(get("/company/1")).andExpect(status().isNotFound());

    }

    @Test
    void shouldReturnAllCompanies() throws Exception{

        mvc.perform(get("/company"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.length()").value(1));


    }

}
