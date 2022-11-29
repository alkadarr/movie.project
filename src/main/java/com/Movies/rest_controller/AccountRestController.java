package com.Movies.rest_controller;

import com.Movies.configs.RestResponse;
import com.Movies.dtos.account.ChangePasswordDto;
import com.Movies.dtos.account.CreateAccountDto;
import com.Movies.dtos.account.RequestTokenDto;
import com.Movies.dtos.account.ResponseTokenDto;
import com.Movies.services.abstraction.AccountService;
//import com.Movies.utility.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationServiceException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.FailedLoginException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/account")
public class AccountRestController {

    @Autowired
    private AccountService accountService;

//    @Autowired
//    private AuthenticationManager authenticationManager;

//    @Autowired
//    private UserDetailsService userDetailsService;

//    @Autowired
//    private JwtToken jwtToken;


    @GetMapping("/all")
    public List<?> getAllAccounts() {
        return accountService.getAll();
    }

    @PostMapping("/register")
    public Object create(@RequestBody CreateAccountDto account) {
        return accountService.create(account);
    }

//    @PutMapping("/change-password")
//    public Object changePassword(Authentication auth, @RequestBody ChangePasswordDto dto) {
//        var username = auth.getName();
//        return accountService.changePassword(username, dto);
//    }

    @PutMapping("/inactive-account/{id}")
    public Object inactiveAccount(@PathVariable Long id) {
        return accountService.inactiveAccount(id);
    }

//    @PostMapping("/authenticate")
//    public ResponseEntity<Object> post(@RequestBody RequestTokenDto dto){
//        try{
//            UsernamePasswordAuthenticationToken token1 = new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
////            authenticationManager.authenticate(token1);
//            UserDetails userDetails = userDetailsService.loadUserByUsername(dto.getUsername());
//            String role = accountService.getAccountRole(userDetails.getUsername());
//            String token = jwtToken.generateToken(dto.getSubject(),userDetails.getUsername(), dto.getSecretKey(), role, dto.getAudience());
//            ResponseTokenDto response = new ResponseTokenDto(dto.getUsername(), role, token);
//            return ResponseEntity.ok(new RestResponse(response));
//        }catch (Exception e){
//            return ResponseEntity.ok(new RestResponse(false, e.getMessage()));
//        }
//
//    }
}
