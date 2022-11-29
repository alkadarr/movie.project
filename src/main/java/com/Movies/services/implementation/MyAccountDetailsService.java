//package com.Movies.services.implementation;
//
//import com.Movies.MyAccountDetails;
//import com.Movies.models.Account;
//import com.Movies.repositories.AccountRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class MyAccountDetailsService implements UserDetailsService {
//
//    @Autowired
//    private AccountRepository accountRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        //nyari User berdasarkan ID
//        Account user = accountRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s tidak ditemukan", username)));
//
//        //mengubah dari User -> UserDetails
//        return new MyAccountDetails(user);
//    }
//}
