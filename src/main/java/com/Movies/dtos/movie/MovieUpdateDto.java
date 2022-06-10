package com.Movies.dtos.movie;

import com.Movies.models.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class MovieUpdateDto implements Serializable {

    private final String title;
    private final Integer productionYear;
    private final Integer duration;
    private final String language;
    private final String releaseDate;
    private final String releaseCountry;

    public static MovieUpdateDto convert(Movie movie) {
        return new MovieUpdateDto(
                movie.getTitle(),
                movie.getProductionYear(),
                movie.getDuration(),
                movie.getLanguage(),
                movie.getReleaseDate().toString(),
                movie.getReleaseCountry()
        );
    }
}
