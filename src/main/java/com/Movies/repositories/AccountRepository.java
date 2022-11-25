package com.Movies.repositories;

import com.Movies.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByUsername(String username);

    @Query(value = """
            SELECT DISTINCT acc.role
            FROM Account acc
            """)
    List<String> getRoleDropdown();
}