package com.Movies.models;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MovieGenreId implements Serializable {
    private static final long serialVersionUID = 2653587873083040747L;
    @Column(name = "GenreID", nullable = false)
    private Integer genreID;

    @Column(name = "MovieID", nullable = false, length = 10)
    private String movieID;

    public Integer getGenreID() {
        return genreID;
    }

    public void setGenreID(Integer genreID) {
        this.genreID = genreID;
    }

    public String getMovieID() {
        return movieID;
    }

    public void setMovieID(String movieID) {
        this.movieID = movieID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MovieGenreId entity = (MovieGenreId) o;
        return Objects.equals(this.genreID, entity.genreID) &&
                Objects.equals(this.movieID, entity.movieID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(genreID, movieID);
    }

}