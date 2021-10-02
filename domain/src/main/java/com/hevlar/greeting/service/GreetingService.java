package com.hevlar.greeting.service;

import com.hevlar.greeting.model.Greeting;
import com.hevlar.greeting.repository.GreetingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GreetingService {

    private final GreetingRepository greetingRepository;

    public String greet(String name){
        return "Hello, " +
                ((name == null || name.isBlank()) ? "World" : name);
    }

    public Greeting saveGreeting(Greeting greeting){
        return greetingRepository.save(greeting);
    }

    public List<Greeting> findGreeting(String name){
        return greetingRepository.findByName(name);
    }
}
