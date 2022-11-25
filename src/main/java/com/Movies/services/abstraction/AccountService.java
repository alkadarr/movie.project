package com.Movies.services.abstraction;

import com.Movies.dtos.account.ChangePasswordDto;
import com.Movies.dtos.account.CreateAccountDto;

import java.util.List;

public interface AccountService {

    public List<?> getAll();
    public Object create(CreateAccountDto registrant);
    public Object changePassword(String username, ChangePasswordDto dto);
    public Object inactiveAccount(Long id);
    public String getAccountRole(String username);

}
