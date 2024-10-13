package com.website.service;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    UserService userService = new UserService();

    @Test
    void getUserInfoById() {
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/test/user.csv")
    void getUserName(int id, String name) {
        assertEquals("testName", userService.getUserName(id));

    }
}