package com.mahad.jobmanager.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

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
        mvc.perform(get("/user/1"))
        .andExpect(status().isOk());
    }


}
