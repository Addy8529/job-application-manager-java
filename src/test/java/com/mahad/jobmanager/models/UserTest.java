package com.mahad.jobmanager.models;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {

    private User user;

    @BeforeEach
    void createUser(){
        this.user = new User(0,"user", "password");
    }

    @Test
    void testGetId() {
        assertThat(this.user.getId()).isEqualTo(0);
    }

    @Test
    void testGetName() {
        assertThat(this.user.getName()).isEqualTo("user");
    }

    @Test
    void testGetPassword() {
        assertThat(this.user.getPassword()).isEqualTo("password");
    }

    @Test
    void testSetName() {
        this.user.setName("newName");
        assertThat(this.user.getName()).isEqualTo("newName");
    }

    @Test
    void testSetPassword() {
        this.user.setPassword("newPassword");
        assertThat(this.user.getPassword()).isEqualTo("newPassword");
    }
}
