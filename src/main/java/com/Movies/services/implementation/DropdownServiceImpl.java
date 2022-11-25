package com.Movies.services.implementation;

import com.Movies.repositories.AccountRepository;
import com.Movies.services.abstraction.DropdownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DropdownServiceImpl implements DropdownService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<String> getRoleList() {
        return accountRepository.getRoleDropdown();
    }
}
