package com.Movies.repositories;

import com.Movies.models.Rating;
import com.Movies.models.RatingId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, RatingId> {

    Optional<Rating> findByReviewerIdAndMovieId(Long reviewerId, String movieId);

}