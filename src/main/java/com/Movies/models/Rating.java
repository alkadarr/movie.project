package com.Movies.models;

import com.Movies.dtos.rating.RatingHeaderDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
    @EmbeddedId
    private RatingId id;

    @MapsId("movieID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MovieID", nullable = false)
    private Movie movie;

    @MapsId("reviewerID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ReviewerID", nullable = false)
    private Reviewer reviewer;

    @Column(name = "Stars")
    private Integer stars;

    public Rating(Integer stars) {
        this.stars = stars;
    }

}