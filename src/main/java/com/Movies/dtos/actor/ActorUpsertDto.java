package com.Movies.dtos.actor;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ActorUpsertDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String gender;
}
