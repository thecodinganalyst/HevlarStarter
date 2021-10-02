package com.hevlar.greeting.repository;

import com.hevlar.greeting.model.Greeting;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.is;

@DataJpaTest
class GreetingRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private GreetingRepository greetingRepository;

    @Test
    void findByName() {
        Greeting greeting = new Greeting("World", LocalDateTime.now(), "Hello, World");
        entityManager.persist(greeting);
        entityManager.flush();

        Greeting found = greetingRepository.findByName("World").get(0);

        assertThat(found.getGreeting(), is("Hello, World"));
    }
}
