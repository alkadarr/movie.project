package com.Movies.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class RatingId implements Serializable {
    private static final long serialVersionUID = -8796374811763050936L;

    @Column(name = "MovieID", nullable = false, length = 10)
    private String movieID;

    @Column(name = "ReviewerID", nullable = false)
    private Long reviewerID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RatingId entity = (RatingId) o;
        return Objects.equals(this.reviewerID, entity.reviewerID) &&
                Objects.equals(this.movieID, entity.movieID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reviewerID, movieID);
    }

}