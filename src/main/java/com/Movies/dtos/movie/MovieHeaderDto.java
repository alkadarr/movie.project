package com.Movies.dtos.movie;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter @Setter
public class MovieHeaderDto implements Serializable {

    private String id;
    private String title;
    private Integer productionYear;
    private Integer duration;
    private String language;
    private LocalDate releaseDate;
    private String releaseCountry;


    public MovieHeaderDto(String id, String title, Integer productionYear, Integer duration, String language, LocalDate releaseDate, String releaseCountry) {
        this.id = id;
        this.title = title;
        this.productionYear = productionYear;
        this.duration = duration;
        this.language = language;
        this.releaseDate = releaseDate;
        this.releaseCountry = releaseCountry;
    }

}
