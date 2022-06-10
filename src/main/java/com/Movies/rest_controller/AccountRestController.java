package com.Movies.rest_controller;

import com.Movies.dtos.account.ChangePasswordDto;
import com.Movies.dtos.account.CreateAccountDto;
import com.Movies.services.abstraction.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
public class AccountRestController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/all")
    public List<?> getAllAccounts() {
        return accountService.getAll();
    }

    @PostMapping("/register")
    public Object create(@RequestBody CreateAccountDto account) {
        return accountService.create(account);
    }

    @PutMapping("/change-password")
    public Object changePassword(Authentication auth, @RequestBody ChangePasswordDto dto) {
        var username = auth.getName();
        return accountService.changePassword(username, dto);
    }

    @PutMapping("/inactive-account/{id}")
    public Object inactiveAccount(@PathVariable Long id) {
        return accountService.inactiveAccount(id);
    }
}
