package com.Movies.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "[Movie Genres]")
@Getter @Setter
public class MovieGenre {
    @EmbeddedId
    private MovieGenreId id;

    @MapsId("genreID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "GenreID", nullable = false)
    private Genre genre;

    @MapsId("movieID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MovieID", nullable = false)
    private Movie movie;

}