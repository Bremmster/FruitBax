package com.karlson.fruitbax.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.karlson.fruitbax.model.FruitDTO;
import com.karlson.fruitbax.model.UpdateFruitDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class FruitControllerTest {


    private final String API = "/fruitbax/fruit";
    private final ObjectMapper mapper = new ObjectMapper();

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

    @Test
    void postCreateOneFruit() throws Exception {
        var payload = new FruitDTO("Pineapple");

        this.mvc.perform(post(API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(payload))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").exists());
    }

    @Test
    void failPostCreateOneFruitWithBadFruit() throws Exception {
        var payload = "Pinecone";

        this.mvc.perform(post(API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(payload))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void putOneValidFruit() throws Exception {
        String newName = "Strawberry";
        var payload = new UpdateFruitDTO("Banana", newName);

        this.mvc.perform(put(API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(payload))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(newName));
    }

    @Test
    void putOneValidFruitThatExistsMakeSureCaseIsCorrect() throws Exception {
        String newName = "mellon";
        String expected = "Mellon";
        var payload = new UpdateFruitDTO("Cherry", newName);

        this.mvc.perform(put(API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(payload))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(expected));
    }

    @Test
    void putOneFruitThatAlreadyExistsShouldFail() throws Exception {
        // Test try to replace the formatName with one fruit that already exists.
        String newName = "Etrog";

        var payload = new UpdateFruitDTO("Durian", newName);

        this.mvc.perform(put(API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(payload))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void putOneFruitThatAlreadyExistsIgnoreCaseShouldFail() throws Exception {
        /* This test try to replace the formatName with one fruit that already exists.
            Make sure the case is ignored of the new fruit and old fruit. */

        String newName = "apple";

        var payload = new UpdateFruitDTO("Figs", newName);

        this.mvc.perform(put(API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(payload))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteOneFruitThatExists() throws Exception {
        var payload = new FruitDTO("huckleberry");
        this.mvc.perform(delete(API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(payload))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteOneFruitThatDoesNotExists() throws Exception {
        var payload = new FruitDTO("Unicorn");
        this.mvc.perform(delete(API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(payload))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}