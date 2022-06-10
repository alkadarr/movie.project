package com.Movies.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reviewer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID", nullable = false)
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "UserID", nullable = false)
    private Account account;

    @Column(name = "FirstName", nullable = false, length = 20)
    private String firstName;

    @Column(name = "LastName", length = 20)
    private String lastName;

    @OneToMany(mappedBy = "reviewer")
    private List<Rating> ratings;

    public Reviewer(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Reviewer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String fetchFullName() {
        return firstName + " " + lastName;
    }
}