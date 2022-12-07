package com.Movies.services.abstraction;

import com.Movies.dtos.movie.MovieDetailsDto;
import com.Movies.dtos.movie.MovieHeaderDto;
import com.Movies.dtos.movie.MovieInsertDto;
import com.Movies.dtos.movie.MovieUpdateDto;

import java.util.HashMap;
import java.util.List;

public interface MovieService {

    // crud methods

    public List<MovieDetailsDto> getAllMovies();
    public Object getAllMoviesWithPagination(String search,String releaseCountry, Integer page, Integer size, String sortBy, String sortType);
    public MovieDetailsDto getMovieById(String id);
    public MovieInsertDto insertMovie(MovieInsertDto movie);
    public MovieUpdateDto updateMovie(String id, MovieUpdateDto movie);
    public void deleteMovie(String id);

    //custom methods

    public Object getMoviesByGenre(String genreId);

    public MovieHeaderDto addActorToMovie(String movieId, Integer actorId, String role);
    public Object removeActorFromMovie(String movieId, Integer actorId);

    public MovieHeaderDto addGenreToMovie(String movieId, Integer genreId);
    public Object removeGenreFromMovie(String movieId, Integer genreId);

    public MovieHeaderDto addDirectorToMovie(String movieId, Integer directorId);
    public Object removeDirectorFromMovie(String movieId, Integer directorId);

}
