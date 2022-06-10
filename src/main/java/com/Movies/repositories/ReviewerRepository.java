package com.Movies.repositories;

import com.Movies.models.Reviewer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ReviewerRepository extends JpaRepository<Reviewer, Long> {

    @Query(value = """
            SELECT *
            FROM Reviewer r
            JOIN Account a ON a.ID = r.UserID
            WHERE a.Username = :username
            """, nativeQuery = true)
    Optional<Reviewer> findByAccountUsername(String username);
}