package com.Movies.dtos.director;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DirectorHeaderDto {

    private Integer id;
    private String firstName;
    private String lastName;

}
