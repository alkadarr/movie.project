package com.Movies.services.implementation;

import com.Movies.dtos.account.AccountHeaderDto;
import com.Movies.dtos.account.ChangePasswordDto;
import com.Movies.dtos.account.CreateAccountDto;
import com.Movies.models.Account;
import com.Movies.repositories.AccountRepository;
import com.Movies.services.abstraction.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class AccountServiceImplementation implements AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<?> getAll() {
        return accountRepository.findAll().stream()
                .map(account -> new AccountHeaderDto(
                        account.getId(),
                        account.getUsername(),
                        account.getRole(),
                        account.isEnabled() ? "Active" : "Inactive"))
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public Object create(CreateAccountDto dto) {
        boolean isExist = accountRepository.findByUsername(dto.getUsername()).isPresent();
        if (isExist) {
            throw new RuntimeException(String.format("Username %s already exists", dto.getUsername()));
        } else {
            Account account = new Account(
                    dto.getUsername(),
                    dto.getPassword(),
                    dto.getRole());
            account.setPassword(passwordEncoder.encode(account.getPassword()));
            accountRepository.save(account);
            return new AccountHeaderDto(account.getId(),account.getUsername(),account.getRole(),account.isEnabled() ? "Active" : "Inactive");
        }
    }

    @Override
    public Object changePassword(String username, ChangePasswordDto dto) {
        Account user = accountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s tidak ditemukan", username)));

        var cekPassword = passwordEncoder.matches(dto.getOldPassword(), user.getPassword());
        if(cekPassword){
            user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
            accountRepository.save(user);
            return "Password changed successfully";
        } else {
            throw new RuntimeException("Old password is wrong");
        }
    }

    @Override
    public Object inactiveAccount(Long id) {
        Account user = accountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Account with id : %s not found", id)));
        user.setEnabled(false);
        accountRepository.save(user);
        return new AccountHeaderDto(
                user.getId(),
                user.getUsername(),
                user.getRole(),
                user.isEnabled() ? "Active" : "Inactive");
    }
}
