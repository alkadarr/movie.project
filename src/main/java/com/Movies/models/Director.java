package com.Movies.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "FirstName", nullable = false, length = 20)
    private String firstName;

    @Column(name = "LastName", length = 20)
    private String lastName;

    @ManyToMany(mappedBy = "directors")
    private List<Movie> movies;

    public String fetchFullname(){
        return firstName+" "+lastName;
    }

}