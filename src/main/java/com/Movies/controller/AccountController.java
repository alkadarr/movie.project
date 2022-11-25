package com.Movies.controller;

import com.Movies.dtos.account.CreateAccountDto;
import com.Movies.services.abstraction.AccountService;
import com.Movies.services.abstraction.DropdownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accSvc;
    @Autowired
    private DropdownService dropdownSvc;

    @GetMapping("/loginForm")
    public String loginForm(Model model) {
        return "account/login-form";
    }

    @GetMapping("/accessDenied")
    public String accessDenied(Model model) {
        return "account/access-denied";
    }

    @GetMapping("/registerForm")
    public String registerForm(Model model) {
        CreateAccountDto dto = new CreateAccountDto();
        List<String> roleDropdown = dropdownSvc.getRoleList();
        model.addAttribute("roleDropdown", roleDropdown);
        model.addAttribute("account", dto);
        return "account/register-form";
    }

//    @PostMapping("/register")
//    public String register(@Valid @ModelAttribute("account") RegisterDTO dto,
//                           BindingResult bindingResult, Model model) {
//        if(bindingResult.hasErrors()) {
//            registerForm(model);
//        }
//        accSvc.registerAccount(dto);
//        return "redirect:/account/loginForm";
//    }
}
