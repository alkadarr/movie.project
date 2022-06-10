package com.Movies.repositories;

import com.Movies.dtos.movie.MovieHeaderDto;
import com.Movies.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, String> {

    // native queries
    @Query(value = """
           UPDATE [Movie Cast]
           SET Role = :role
           WHERE MovieId = :movieId AND ActorId = :actorId
           """, nativeQuery = true)
    void addRoleToMovieCast(@Param("movieId") String movieId,
                            @Param("actorId") Integer actorId,
                            @Param("role") String role);

    @Query(value = """
            SELECT AVG(r.Stars)
            FROM Rating r
            WHERE r.MovieID = :movieId""", nativeQuery = true)
    Double getAverageRating(@Param("movieId") String movieId);

    // HQl query
    @Query(value = """
            SELECT new com.Movies.dtos.movie.MovieHeaderDto(m.id, m.title, m.productionYear, m.duration, m.language, m.releaseDate, m.releaseCountry)
            FROM MovieGenre mg
            INNER JOIN mg.genre g
            INNER JOIN mg.movie m
            WHERE g.title LIKE :genreTitle
            """)
    Optional<List<MovieHeaderDto>> findMoviesByGenre(@Param("genreTitle") String genreTitle);


}