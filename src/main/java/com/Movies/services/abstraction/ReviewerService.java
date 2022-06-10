package com.Movies.services.abstraction;

import com.Movies.dtos.reviewer.CompleteProfileDto;

import java.util.List;

public interface ReviewerService {

    // crud methods

    public List<?> getAll();
    public Object getById(Object id);
    public Object completeProfile(String id, CompleteProfileDto dto);
    public boolean delete(Object id);

    // custom methods

    public Object addRatingToMovie(String username, String movieId, Integer stars);

}
