package com.Movies.dtos.director;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class DirectorInsertDto {

    private String firstName;
    private String lastName;

}
