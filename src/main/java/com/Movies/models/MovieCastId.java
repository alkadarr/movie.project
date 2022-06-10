package com.Movies.models;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MovieCastId implements Serializable {
    private static final long serialVersionUID = 2047104705171487120L;
    @Column(name = "ActorID", nullable = false)
    private Integer actorID;

    @Column(name = "MovieID", nullable = false, length = 10)
    private String movieID;

    public Integer getActorID() {
        return actorID;
    }

    public void setActorID(Integer actorID) {
        this.actorID = actorID;
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
        MovieCastId entity = (MovieCastId) o;
        return Objects.equals(this.actorID, entity.actorID) &&
                Objects.equals(this.movieID, entity.movieID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actorID, movieID);
    }

}