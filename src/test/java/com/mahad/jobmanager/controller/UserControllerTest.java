package com.mahad.jobmanager.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.mahad.jobmanager.models.User;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void shouldHavePostMapping() throws Exception{
        String user = """
                {
        "name": "tester",
        "password": "pass123"
                }
                """;
        mvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON).content(user))
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

        mvc.perform(put("/user/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content("""
                {
                "id":null,
                "name": "tester",
                "password": "pass456"
                }
                """)).andExpect(status().isNoContent());

        mvc.perform(get("/user/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.password").value("pass456"));
    }

    @Test
    void shouldDeleteExistingUser() throws Exception{
        String url = createUserAndGetLocation("deleteTester", "delete123");

        mvc.perform(get(url)).andExpect(status().isOk());
        mvc.perform(delete(url)).andExpect(status().isNoContent());
        mvc.perform(get(url)).andExpect(status().isNotFound()); 
    }

   private String createUserAndGetLocation( String name, String password)throws Exception{

        return mvc.perform(post("/user")
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
