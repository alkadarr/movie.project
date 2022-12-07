package com.Movies.models;

import com.Movies.dtos.actor.ActorCastDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Movie {
    @Id
    @Column(name = "ID", nullable = false, length = 10)
    private String id;

    @Column(name = "Title", length = 50, nullable = false)
    private String title;

    @Column(name = "ProductionYear")
    private Integer productionYear;

    @Column(name = "Duration")
    private Integer duration;

    @Column(name = "Language", length = 50)
    private String language;

    @Column(name = "ReleaseDate")
    private LocalDate releaseDate;

    @Column(name = "ReleaseCountry", length = 5)
    private String releaseCountry;

    @ManyToMany
    @JoinTable(name = "[Movie Genres]",
            joinColumns = @JoinColumn(name = "MovieID"),
            inverseJoinColumns = @JoinColumn(name = "GenreID"))
    private List<Genre> genres;

    @OneToMany(mappedBy = "movie")
    private List<Rating> ratings;

    @ManyToMany
    @JoinTable(name = "[Movie Direction]",
            joinColumns = @JoinColumn(name = "MovieID"),
            inverseJoinColumns = @JoinColumn(name = "DirectorID"))
    private List<Director> directors;

    @ManyToMany
    @JoinTable(name = "[Movie Cast]",
            joinColumns = @JoinColumn(name = "MovieID"),
            inverseJoinColumns = @JoinColumn(name = "ActorID"))
    private List<Actor> actors;

    @OneToMany(mappedBy = "movie")
    private List<MovieCast> movieCasts;

    public Movie(String id, String title, Integer productionYear, Integer duration, String language, LocalDate releaseDate, String releaseCountry) {
        this.id = id;
        this.title = title;
        this.productionYear = productionYear;
        this.duration = duration;
        this.language = language;
        this.releaseDate = releaseDate;
        this.releaseCountry = releaseCountry;
    }

    public List<String> getAllGenreTitles() {
        return genres.stream()
                .map(Genre::getTitle)
                .collect(Collectors.toList());
    }

    public List<String> getAllDirectorsName(){
        return directors.stream()
                .map(Director::fetchFullname)
                .collect(Collectors.toList());
    }

    public List<ActorCastDto> getAllActorsName(){
        return movieCasts.stream()
                .map(MovieCast -> new ActorCastDto(
                        MovieCast.getActor().fetchFullname(),
                        MovieCast.getRole()))
                .collect(Collectors.toList());
    }

    public Double getRatingRate(){
        int totalRating = 0;
        for (Rating rating : ratings){
            totalRating = totalRating + rating.getStars();
        }
        if (ratings.size() == 0){
            return null;
        } else {
            return (double) (totalRating / ratings.size());
        }
    }

}