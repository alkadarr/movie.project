package com.Movies.dtos.account;

import lombok.Data;

@Data
public class CreateAccountDto {

    private String username;
    private String password;
    private String role;

    public CreateAccountDto(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
