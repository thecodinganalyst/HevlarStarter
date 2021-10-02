package com.hevlar.greeting.service;

import com.hevlar.greeting.repository.GreetingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class GreetingServiceTest {

    GreetingService greetingService;

    @MockBean
    GreetingRepository greetingRepository;

    @BeforeEach
    void setUp(){
        greetingService = new GreetingService(greetingRepository);
    }

    @Test
    void greet_returns_hello_john_when_name_is_john() {
        assertThat(greetingService.greet("John"), is("Hello, John"));
    }

    @Test
    void greet_returns_hello_world_when_name_is_null() {
        assertThat(greetingService.greet(null), is("Hello, World"));
    }

    @Test
    void greet_returns_hello_world_when_name_is_blank() {
        assertThat(greetingService.greet(" "), is("Hello, World"));
    }



}
