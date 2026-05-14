package com.mahad.jobmanager.controller;

import com.mahad.jobmanager.repository.CompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CompanyControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CompanyRepository companyRepository;

    private static final String VALID_COMPANY_JSON = """
            {
                "id": null,
                "name": "APPLE",
                "url": "https://apple.com",
                "description": "test",
                "numberOfEmployees": 10
            }
            """;

    @BeforeEach
    void cleanDatabase() {
        companyRepository.deleteAll();
    }

    @Test
    void shouldCreateCompany() throws Exception {
        mvc.perform(post("/company")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(VALID_COMPANY_JSON))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

    @Test
    void shouldReturnCompanyIfItExists() throws Exception {
        String location = createCompanyAndReturnLocation();

        mvc.perform(get(location))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("APPLE"))
                .andExpect(jsonPath("$.url").value("https://apple.com"))
                .andExpect(jsonPath("$.description").value("test"))
                .andExpect(jsonPath("$.numberOfEmployees").value(10));
    }

    @Test
    void shouldReturnNotFoundIfCompanyDoesNotExist() throws Exception {
        mvc.perform(get("/company/99999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldUpdateCompanyIfItExists() throws Exception {
        String location = createCompanyAndReturnLocation();

        String updatedCompanyJson = """
                {
                    "id": null,
                    "name": "IBM",
                    "url": "https://ibm.com",
                    "description": "updated",
                    "numberOfEmployees": 100
                }
                """;

        mvc.perform(put(location)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedCompanyJson))
                .andExpect(status().isNoContent());

        mvc.perform(get(location))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("IBM"))
                .andExpect(jsonPath("$.url").value("https://ibm.com"))
                .andExpect(jsonPath("$.description").value("updated"))
                .andExpect(jsonPath("$.numberOfEmployees").value(100));
    }

    @Test
    void shouldReturnNotFoundWhenUpdatingNonExistingCompany() throws Exception {
        mvc.perform(put("/company/99999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(VALID_COMPANY_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldDeleteCompanyIfItExists() throws Exception {
        String location = createCompanyAndReturnLocation();

        mvc.perform(delete(location))
                .andExpect(status().isNoContent());

        mvc.perform(get(location))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnAllCompanies() throws Exception {
        createCompanyAndReturnLocation();

        mvc.perform(get("/company"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(1));
    }

    private String createCompanyAndReturnLocation() throws Exception {
        MvcResult result = mvc.perform(post("/company")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(VALID_COMPANY_JSON))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andReturn();

        return result.getResponse().getHeader("Location");
    }
}
