package com.Movies.dtos.actor;

import lombok.Data;

@Data
public class ActorCastDto {
    private String fullName;
    private String role;

    public ActorCastDto(String fullName, String role) {
        this.fullName = fullName;
        this.role = role;
    }
}
