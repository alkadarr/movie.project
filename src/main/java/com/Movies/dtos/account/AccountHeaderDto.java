package com.Movies.dtos.account;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountHeaderDto {

    private Long id;
    private String username;
    private String role;
    private String activeStatus;

}
