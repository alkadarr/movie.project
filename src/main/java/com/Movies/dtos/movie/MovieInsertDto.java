package com.Movies.dtos.movie;

import com.Movies.models.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class MovieInsertDto implements Serializable {
    private final String id;
    private final String title;
    private final Integer productionYear;
    private final Integer duration;
    private final String language;
    private final String releaseDate;
    private final String releaseCountry;

    public static MovieInsertDto convert(Movie movie) {
        return new MovieInsertDto(
                movie.getId(),
                movie.getTitle(),
                movie.getProductionYear(),
                movie.getDuration(),
                movie.getLanguage(),
                movie.getReleaseDate().toString(),
                movie.getReleaseCountry()
        );
    }
}
