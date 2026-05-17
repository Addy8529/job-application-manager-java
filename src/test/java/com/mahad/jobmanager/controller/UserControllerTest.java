package com.mahad.jobmanager.controller;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import jakarta.servlet.ServletException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.mahad.jobmanager.repository.UserRepository;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void cleanDatabase() {
        userRepository.deleteAll();
    }

    @Test
    void shouldHavePostMapping() throws Exception{
        String user = """
                {
        "name": "tester",
        "password": "pass123"
                }
                """;
        mvc.perform(post("/user").with(csrf()).contentType(MediaType.APPLICATION_JSON).content(user))
        .andExpect(status().isCreated())
        .andExpect(header().exists("location"));
    }

    @Test
    void shouldActuallyCreateUser() throws Exception{
        String name = "Tester";
        String password = "pass123";
        String url = createUserAndGetLocation( name, password);
        mvc.perform(get(url))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").exists())
        .andExpect(jsonPath("$.name").value(name))
        .andExpect(jsonPath("$.password").value(password));
    }

    @Test
    void shouldUpdateExistingUser() throws Exception{
        String location = createUserAndGetLocation("tester", "pass123");

        mvc.perform(put(location).with(csrf())
        .contentType(MediaType.APPLICATION_JSON)
        .content("""
                {
                "id":null,
                "name": "tester",
                "password": "pass456"
                }
                """)).andExpect(status().isNoContent());

        mvc.perform(get(location))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("tester"))
        .andExpect(jsonPath("$.password").value("pass456"));
    }

    @Test
    void shouldReturnNotFoundWhenUserDoesNotExist() throws Exception {
        mvc.perform(get("/user/99999"))
        .andExpect(status().isNotFound());
    }

    @Test
    void shouldSurfacePersistenceFailureWhenUpdatingNonExistingUser() {
        assertThrows(ServletException.class, () ->
            mvc.perform(put("/user/99999").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(UserJson("upserted", "pass456")))
        );
    }

    @Test
    void shouldReturnNoContentWhenDeletingNonExistingUser() throws Exception {
        mvc.perform(delete("/user/99999").with(csrf()))
        .andExpect(status().isNoContent());
    }

    @Test
    void shouldDeleteExistingUser() throws Exception{
        String url = createUserAndGetLocation("deleteTester", "delete123");

        mvc.perform(get(url)).andExpect(status().isOk());
        mvc.perform(delete(url).with(csrf())).andExpect(status().isNoContent());
        mvc.perform(get(url)).andExpect(status().isNotFound()); 
    }

   private String createUserAndGetLocation( String name, String password)throws Exception{

        return mvc.perform(post("/user").with(csrf())
        .contentType(MediaType.APPLICATION_JSON).content(UserJson(name, password)))
        .andExpect(status().isCreated())
        .andExpect(header().exists("location"))
        .andReturn().getResponse().getHeader("location");

   }


   private String UserJson(String name, String password) {
        return """
                {
                    "id": null,
                    "name": "%s",
                    "password": "%s"
                }
                """.formatted(name, password);
    }


}
