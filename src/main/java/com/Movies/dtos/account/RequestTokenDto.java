package com.Movies.dtos.account;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class RequestTokenDto {

    @Getter @Setter private String username;

    @Getter @Setter private String password;

    @Getter @Setter private String subject;

    @Getter @Setter private String secretKey;

    @Getter @Setter private String audience;

}
