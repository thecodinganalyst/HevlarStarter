package com.hevlar.greeting.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.hevlar.greeting.model.Greeting;
import com.hevlar.greeting.service.GreetingService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.any;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(value = GreetingRestController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GreetingRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private GreetingService greetingService;

    ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();

    @BeforeAll
    void setUp(){
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Test
    void greeting_isOk() throws Exception {

        given(greetingService.greet(any())).willReturn("Hello, World");

        mvc.perform(
                get("/greeting"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, World"));
    }

    @Test
    void saveGreeting_will_return_greeting() throws Exception {

        Greeting sampleGreeting = new Greeting("Alex", LocalDateTime.of(2021,1,1,9,0), "Hello, Alex");
        String greetingJson = objectMapper.writeValueAsString(sampleGreeting);

        given(greetingService.saveGreeting(any())).willReturn(sampleGreeting);

        mvc.perform(
                post("/saveGreeting").contentType(MediaType.APPLICATION_JSON).content(greetingJson))
                .andExpect(status().isOk())
                .andExpect(content().string(greetingJson))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void findGreeting_will_return_greeting() throws Exception {

        Greeting sampleGreeting = new Greeting("Alex", LocalDateTime.of(2021,1,1,9,0), "Hello, Alex");
        String greetingListJson = objectMapper.writeValueAsString(List.of(sampleGreeting));

        given(greetingService.findGreeting(any())).willReturn(List.of(sampleGreeting));

        mvc.perform(
                get("/findGreeting").queryParam("name", "Alex"))
                .andExpect(status().isOk())
                .andExpect(content().string(greetingListJson));
    }
}