package com.Movies.dtos.genre;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenreUpdateDto {
    private Integer id;
    private String title;
}
