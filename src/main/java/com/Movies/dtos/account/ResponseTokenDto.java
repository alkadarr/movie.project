package com.Movies.dtos.account;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter @Setter
public class ResponseTokenDto {

    private String username;
    private String role;
    private String token;

}
