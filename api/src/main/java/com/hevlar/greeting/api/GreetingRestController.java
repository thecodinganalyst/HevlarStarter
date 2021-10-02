package com.hevlar.greeting.api;

import com.hevlar.greeting.model.Greeting;
import com.hevlar.greeting.service.GreetingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
public class GreetingRestController {

    private final GreetingService greetingService;

    public GreetingRestController(GreetingService greetingService){
        this.greetingService = greetingService;
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(value = "name", defaultValue = "World") String name){
        return greetingService.greet(name);
    }

    @PostMapping("/saveGreeting")
    public Greeting saveGreeting(@RequestBody Greeting greeting){
        return greetingService.saveGreeting(greeting);
    }

    @GetMapping("/findGreeting")
    public List<Greeting> findGreeting(@RequestParam(value = "name") String name){
        return greetingService.findGreeting(name);
    }

}
