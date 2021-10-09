package com.hevlar.greeting.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o.getClass() != this.getClass()) return false;
        Greeting g = (Greeting)o;
        return (Objects.equals(this.name, g.name) && Objects.equals(this.dateTime, g.dateTime) && Objects.equals(this.greeting, g.greeting));
    }

    @Override
    public int hashCode() {
        return Long.hashCode(this.id != null ? this.id : 1) * this.name.hashCode() * this.dateTime.hashCode() * this.greeting.hashCode();
    }
}
