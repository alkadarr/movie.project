package com.Movies.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter @Setter
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "FirstName", length = 20, nullable = false)
    private String firstName;

    @Column(name = "LastName", length = 20)
    private String lastName;

    @Column(name = "Gender", length = 10)
    private String gender;

    @ManyToMany(mappedBy = "actors")
    private List<Movie> movies;

    @OneToMany(mappedBy = "actor")
    private List<MovieCast> movieCasts;

    public Actor(String firstName, String lastName, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    public String fetchFullname(){
        return firstName+" "+lastName;
    }

}