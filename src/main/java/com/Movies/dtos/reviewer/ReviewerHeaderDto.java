package com.Movies.dtos.reviewer;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewerHeaderDto {

    private Long userId;
    private String firstName;
    private String lastName;
}
