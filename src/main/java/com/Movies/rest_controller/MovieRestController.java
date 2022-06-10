package com.Movies.rest_controller;

import com.Movies.dtos.RestResponse;
import com.Movies.dtos.movie.MovieDetailsDto;
import com.Movies.dtos.movie.MovieHeaderDto;
import com.Movies.dtos.movie.MovieInsertDto;
import com.Movies.dtos.movie.MovieUpdateDto;
import com.Movies.services.abstraction.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieRestController {

    private final MovieService service;

    @Autowired
    public MovieRestController(MovieService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<RestResponse<List<MovieDetailsDto>>> getAllMovies() {
        try {
            var movies = service.getAllMovies();
            return ResponseEntity.ok().
                    body(new RestResponse<>(movies, "Successfully retrieved all movies.","200"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body(new RestResponse<>(null, e.getMessage(),"500"));
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<RestResponse<MovieDetailsDto>> getMovieById(@PathVariable String id) {
        try {
            var movie = service.getMovieById(id);
            return ResponseEntity.ok().
                    body( new RestResponse<>(movie, "Movie found successfully.", "200"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body(new RestResponse<>(null, e.getMessage(), "404"));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<RestResponse<MovieInsertDto>> createMovie(@RequestBody MovieInsertDto movie) {
        try {
            var newMovie = service.insertMovie(movie);
            return ResponseEntity.ok().
                    body(new RestResponse<>(newMovie, "Movie created successfully.", "200"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body(new RestResponse<>(null, e.getMessage(), "500"));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RestResponse<MovieUpdateDto>> updateMovie(@PathVariable String id, @RequestBody MovieUpdateDto movie) {
        try {
            var updatedMovie = service.updateMovie(id, movie);
            return ResponseEntity.ok().
                    body(new RestResponse<>(updatedMovie, "Movie updated successfully.", "200"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(new RestResponse<>(null, e.getMessage(), "400"));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<RestResponse<Object>> deleteMovie(@PathVariable String id) {
        try {
            service.deleteMovie(id);
            return ResponseEntity.ok().
                    body(new RestResponse<>(null, "Movie deleted successfully.", "200"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(new RestResponse<>(null, e.getMessage(), "400"));
        }
    }

    @PutMapping("/add-genre")
    public ResponseEntity<RestResponse<MovieHeaderDto>> addGenreToMovie(@RequestParam String id, @RequestParam Integer genreId) {
        try {
            var updatedMovie = service.addGenreToMovie(id, genreId);
            return ResponseEntity.ok().
                    body(new RestResponse<>(updatedMovie, "Genre added successfully.", "200"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(new RestResponse<>(null, e.getMessage(), "400"));
        }
    }

    @PutMapping("/remove-genre")
    public ResponseEntity<RestResponse<MovieHeaderDto>> removeGenreFromMovie(@RequestParam String id, @RequestParam Integer genreId) {
        try {
            var updatedMovie = service.removeGenreFromMovie(id, genreId);
            return ResponseEntity.ok().
                    body(new RestResponse<>("Genre removed successfully.", "200"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(new RestResponse<>(e.getMessage(), "400"));
        }
    }

    @PutMapping("/add-actor")
    public ResponseEntity<RestResponse<MovieHeaderDto>> addActorToMovie(@RequestParam String id,
                                                                        @RequestParam Integer actorId,
                                                                        @RequestParam String role) {
        try {
            var updatedMovie = service.addActorToMovie(id, actorId, role);
            return ResponseEntity.ok().
                    body(new RestResponse<>(updatedMovie, "Actor added successfully.", "200"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(new RestResponse<>(null, e.getMessage(), "400"));
        }
    }

    @PutMapping("/remove-actor")
    public ResponseEntity<RestResponse<MovieHeaderDto>> removeActorFromMovie(@RequestParam String id, @RequestParam Integer actorId) {
        try {
            var updatedMovie = service.removeActorFromMovie(id, actorId);
            return ResponseEntity.ok().
                    body(new RestResponse<>("Actor removed successfully.", "200"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(new RestResponse<>(e.getMessage(), "400"));
        }
    }

    @PutMapping("/add-director")
    public ResponseEntity<RestResponse<MovieHeaderDto>> addDirectorToMovie(@RequestParam String id, @RequestParam Integer directorId) {
        try {
            var updatedMovie = service.addDirectorToMovie(id, directorId);
            return ResponseEntity.ok().
                    body(new RestResponse<>(updatedMovie, "Director added successfully.", "200"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(new RestResponse<>(null, e.getMessage(), "400"));
        }
    }

    @PutMapping("/remove-director")
    public ResponseEntity<RestResponse<MovieHeaderDto>> removeDirectorFromMovie(@RequestParam String id, @RequestParam Integer directorId) {
        try {
            var updatedMovie = service.removeDirectorFromMovie(id, directorId);
            return ResponseEntity.ok().
                    body(new RestResponse<>("Director removed successfully.", "200"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(new RestResponse<>(e.getMessage(), "400"));
        }
    }

    @GetMapping("/get-by-genre")
    public ResponseEntity<RestResponse<Object>> getMoviesByGenre(@RequestParam String genreTitle) {
        try {
            var movies = service.getMoviesByGenre(genreTitle);
            return ResponseEntity.ok().
                    body(new RestResponse<>(movies, "Movies found successfully.", "200"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(new RestResponse<>(null, e.getMessage(), "400"));
        }
    }



}
