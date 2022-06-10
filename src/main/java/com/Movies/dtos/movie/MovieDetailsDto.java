package com.Movies.dtos.movie;

import com.Movies.dtos.actor.ActorCastDto;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
public class MovieDetailsDto extends MovieHeaderDto {

    private Double movieRating;
    private List<String> movieDirectors;
    private List<ActorCastDto> movieCasts;
    private List<String> movieGenres;

    public MovieDetailsDto(String id, String title, Integer productionYear, Integer duration, String language, LocalDate releaseDate, String releaseCountry) {
        super(id, title, productionYear, duration, language, releaseDate, releaseCountry);
    }

    public MovieDetailsDto(String id, String title, Integer productionYear, Integer duration, String language, LocalDate releaseDate, String releaseCountry, Double movieRating, List<String> movieDirectors, List<ActorCastDto> movieCasts, List<String> movieGenres) {
        super(id, title, productionYear, duration, language, releaseDate, releaseCountry);
        this.movieRating = movieRating;
        this.movieDirectors = movieDirectors;
        this.movieCasts = movieCasts;
        this.movieGenres = movieGenres;
    }
}
