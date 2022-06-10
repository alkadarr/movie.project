package com.Movies.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "[Movie Cast]")
@Getter @Setter
public class MovieCast {
    @EmbeddedId
    private MovieCastId id;

    @MapsId("actorID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ActorID", nullable = false)
    private Actor actor;

    @MapsId("movieID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MovieID", nullable = false)
    private Movie movie;

    @Column(name = "role", length = 30)
    private String role;

    public String getRole() {
        return role;
    }


}