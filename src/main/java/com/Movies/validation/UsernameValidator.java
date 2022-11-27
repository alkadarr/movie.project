package com.Movies.validation;


import com.Movies.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsernameValidator implements ConstraintValidator<Username, String> {

    @Autowired
    private AccountRepository accDao;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        if (accDao.findByUsername(username).isPresent()){
            return false;
        } else {
            return true;
        }
    }
}
