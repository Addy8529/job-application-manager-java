package com.mahad.jobmanager.controller;

import com.mahad.jobmanager.repository.ApplicationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.hamcrest.Matchers.hasItems;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ApplicationControllerTest {

    private static final String BASE_URL = "/app";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ApplicationRepository applicationRepository;

    @BeforeEach
    void cleanDatabase() {
        applicationRepository.deleteAll();
    }

    @Test
    void shouldCreateApplication() throws Exception {
        mvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(applicationJson("Java Developer")))
                .andExpect(status().isCreated())
                .andExpect(jsonContent())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").value("Java Developer"));
    }

    @Test
    void shouldReturnApplicationIfItExists() throws Exception {
        String location = createApplicationAndGetLocation("Software Developer");

        mvc.perform(get(location))
                .andExpect(status().isOk())
                .andExpect(jsonContent())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").value("Software Developer"));
    }

    @Test
    void shouldReturnNotFoundIfApplicationDoesNotExist() throws Exception {
        mvc.perform(get(BASE_URL + "/99999"))
                .andExpect(status().isNotFound());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            """
            { "title": null }
            """,
            """
            { "title": "" }
            """,
            """
            { "title": " " }
            """
    })
    void shouldReturnBadRequestWhenCreatingApplicationWithInvalidTitle(String invalidJson) throws Exception {
        mvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldUpdateApplicationIfItExists() throws Exception {
        String location = createApplicationAndGetLocation("Pentester");

        mvc.perform(put(location)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(applicationJson("Penetration Tester")))
                .andExpect(status().isNoContent());

        mvc.perform(get(location))
                .andExpect(status().isOk())
                .andExpect(jsonContent())
                .andExpect(jsonPath("$.title").value("Penetration Tester"));
    }

    @Test
    void shouldReturnNotFoundWhenUpdatingNonExistingApplication() throws Exception {
        mvc.perform(put(BASE_URL + "/99999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(applicationJson("Pentester")))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldDeleteApplicationIfItExists() throws Exception {
        String location = createApplicationAndGetLocation("Pentester");

        mvc.perform(delete(location))
                .andExpect(status().isNoContent());

        mvc.perform(get(location))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnNotFoundWhenDeletingNonExistingApplication() throws Exception {
        mvc.perform(delete(BASE_URL + "/99999"))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldReturnAllApplications() throws Exception {
        createApplicationAndGetLocation("Java Developer");
        createApplicationAndGetLocation("Backend Developer");

        mvc.perform(get(BASE_URL))
                .andExpect(status().isOk())
                .andExpect(jsonContent())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[*].title").value(hasItems(
                        "Java Developer",
                        "Backend Developer"
                )));
    }

    private String createApplicationAndGetLocation(String title) throws Exception {
        MvcResult result = mvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(applicationJson(title)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andReturn();

        return result.getResponse().getHeader("Location");
    }

    private String applicationJson(String title) {
        return """
                {
                    "title": "%s"
                }
                """.formatted(title);
    }

    private static ResultMatcher jsonContent() {
        return content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON);
    }
}
