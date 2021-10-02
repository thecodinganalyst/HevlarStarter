package com.hevlar.greeting.service;

import com.hevlar.greeting.model.Greeting;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
public class GreetingServiceIntegrationTest {

    @Autowired
    private GreetingService greetingService;

    @Test
    public void GreetingService_saveGreeting_and_returnGreeting_successful(){
        Greeting greeting = new Greeting("World", LocalDateTime.now(), "Hello, World");
        Greeting savedGreeting = greetingService.saveGreeting(greeting);
        assertThat(savedGreeting.getName(), is("World"));

        List<Greeting> retrievedGreetingList = greetingService.findGreeting("World");
        assertThat(retrievedGreetingList.size(), equalTo(1));
        Greeting retrievedGreeting = retrievedGreetingList.get(0);
        assertThat(retrievedGreeting.getGreeting(), is("Hello, World"));
    }

}
