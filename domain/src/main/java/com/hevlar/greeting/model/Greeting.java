package com.hevlar.greeting.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Greeting {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    @Getter
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private LocalDateTime dateTime;

    @Getter
    @Setter
    private String greeting;

    public Greeting() {

    }

    public Greeting(String name, LocalDateTime dateTime, String greeting){
        this.name = name;
        this.dateTime = dateTime;
        this.greeting = greeting;
    }
}
