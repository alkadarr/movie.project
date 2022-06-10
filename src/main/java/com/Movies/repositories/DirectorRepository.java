package com.Movies.repositories;

import com.Movies.models.Director;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DirectorRepository extends JpaRepository<Director, Integer> {

    Optional<Director> findByFirstNameAndLastName(String firstName, String lastName);
}