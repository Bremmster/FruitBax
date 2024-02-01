package com.karlson.fruitbax.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FruitControllerTest {


    private final String API = "/fruitbax/fruit";

    @Autowired
    private MockMvc mvc;

    @Test
    void getAllFruits() throws Exception {
        this.mvc.perform(get(API))
                .andExpect(status().isOk());

    }

    @Test
    void getOneFruitByNameCorrectCase() throws Exception {
        String payload = "/Apple";

        this.mvc.perform(get(API + payload)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").exists());
    }

    @Test
    void getOneFruitByNameAnyCase() throws Exception {
        String payload = "/apPLe";

        this.mvc.perform(get(API + payload)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").exists());
    }

    @Test
    void tryToGetOneFruitThatDontExistByNameIsNotFound() throws Exception {
        String payload = "/Kiwi";

        this.mvc.perform(get(API + payload)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}