package com.Movies.dtos.rating;

import lombok.Data;

import java.io.Serializable;

@Data
public class RatingHeaderDto implements Serializable {

    private final String movieID;
    private final String movieTitle;
    private final Long reviewerID;
    private final String reviewerName;
    private final Integer stars;
}
