package com.Movies.repositories;

import com.Movies.models.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ActorRepository extends JpaRepository<Actor, Integer> {

    Optional<Actor> findByFirstNameAndLastNameAndGender(
            String firstName,
            String lastName,
            String gender
    );
}