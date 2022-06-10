package com.Movies.services.implementation;

import com.Movies.dtos.rating.RatingHeaderDto;
import com.Movies.dtos.reviewer.CompleteProfileDto;
import com.Movies.dtos.reviewer.ReviewerHeaderDto;
import com.Movies.models.Account;
import com.Movies.models.Rating;
import com.Movies.models.RatingId;
import com.Movies.models.Reviewer;
import com.Movies.repositories.AccountRepository;
import com.Movies.repositories.MovieRepository;
import com.Movies.repositories.RatingRepository;
import com.Movies.repositories.ReviewerRepository;
import com.Movies.services.abstraction.ReviewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service("reviewerMenu")
public class ReviewerServiceImplementation implements ReviewerService {

    @Autowired
    private ReviewerRepository reviewerRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<?> getAll() {
        return reviewerRepository.findAll().stream().
                map(reviewer -> new ReviewerHeaderDto(
                        reviewer.getId(),
                        reviewer.getFirstName(),
                        reviewer.getLastName())).
                collect(Collectors.toList());
    }

    @Override
    public Object getById(Object id) {
        return reviewerRepository.findById((Long) id)
                .map(reviewer -> new ReviewerHeaderDto(
                        reviewer.getId(),
                        reviewer.getFirstName(),
                        reviewer.getLastName()))
                .orElseThrow(() -> new EntityNotFoundException(String.format("Reviewer with id : %s not found", id)));
    }

    @Override
    public Object completeProfile(String id, CompleteProfileDto dto) {
        Account account = accountRepository.findByUsername(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Account with username : %s not found", id)));

        Reviewer reviewer = reviewerRepository.findById(account.getId())
                .orElse(new Reviewer(
                        dto.getFirstName(),
                        dto.getLastName())
                );

        reviewer.setAccount(account);
        reviewerRepository.save(reviewer);

        return new ReviewerHeaderDto(
                reviewer.getId(),
                reviewer.getFirstName(),
                reviewer.getLastName()
        );
    }

    @Override
    public boolean delete(Object id) {
        var reviewer = reviewerRepository.findById((Long) id);
        if (reviewer.isPresent()) {
            Account account = accountRepository.findById(reviewer.get().getId())
                    .orElseThrow(() -> new EntityNotFoundException(String.format("Account with id : %s not found", id)));

            account.setEnabled(false);

            accountRepository.save(account);
            reviewerRepository.deleteById((Long) id);
            return true;
        } else {
            throw new EntityNotFoundException(String.format("Reviewer with id : %s not found", id));
        }
    }

    @Override
    public Object addRatingToMovie(String username, String movieId, Integer stars) {
        var movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Movie with id : %s not found", movieId)));

        var reviewer = reviewerRepository.findByAccountUsername(username);
        if (reviewer.isPresent()) {
            var ratingEntity = ratingRepository.findByReviewerIdAndMovieId(reviewer.get().getId(), movieId);

            if (ratingEntity.isPresent()) { // update stars
                ratingEntity.get().setStars(stars);
                ratingRepository.save(ratingEntity.get());

                return new RatingHeaderDto(
                        ratingEntity.get().getMovie().getId(),
                        ratingEntity.get().getMovie().getTitle(),
                        ratingEntity.get().getReviewer().getId(),
                        ratingEntity.get().getReviewer().fetchFullName(),
                        ratingEntity.get().getStars()
                );
            } else { // add new rating
                Rating rating = new Rating(stars);

                rating.setReviewer(reviewer.get());
                rating.setMovie(movie);
                rating.setId(new RatingId(movieId,reviewer.get().getId()));

                ratingRepository.save(rating);

                return new RatingHeaderDto(
                        rating.getMovie().getId(),
                        rating.getMovie().getTitle(),
                        rating.getReviewer().getId(),
                        rating.getReviewer().fetchFullName(),
                        rating.getStars()
                );
            }
        } else {
            throw new EntityNotFoundException(String.format("Reviewer with username : %s not found", movieId));
        }
    }
}
