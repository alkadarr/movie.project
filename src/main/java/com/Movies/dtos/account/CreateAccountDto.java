package com.Movies.dtos.account;

import com.Movies.validation.Username;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class CreateAccountDto {

    @NotEmpty(message = "username is required")
    @Size(min = 5)
    @Username
    private String username;
    @Size(min = 5)
    @NotEmpty(message = "How could you log in without password bruh!")
    private String password;
    @NotEmpty(message = "Choose one la!")
    private String role;

    public CreateAccountDto(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
