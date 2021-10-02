package com.hevlar.greeting.repository;

import com.hevlar.greeting.model.Greeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface GreetingRepository extends JpaRepository<Greeting, Long> {
    List<Greeting> findByName(String name);
}
