package com.Movies.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity                      //WebSecurityConfiguration
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Autowired
    public ApplicationSecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    //authentication -> username, password
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    //authorization -> role / authority
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/actor/all","/api/actor/get/**",
                        "/api/director/all","/api/director/get/**",
                        "/api/genre/all","/api/genre/get/**",
                        "/api/movies/all","/api/movies/get/**",
                        "/api/reviewer/all","api/reviewer/get/**",
                        "/api/movies/get-by-genre").permitAll()
                .antMatchers("/api/account/change-password").authenticated()
                .antMatchers("/api/account/register","/api/account/all","/api/account/inactive-account/**",
                        "/api/actor/insert","/api/actor/update/**","/api/actor/delete/**",
                        "/api/director/insert","/api/director/update/**","/api/director/delete/**",
                        "/api/genre/insert","/api/genre/update/**","/api/genre/delete/**",
                        "/api/movies/create","/api/movies/update/**","/api/movies/delete/**",
                        "/api/reviewer/delete/**","/api/movies/add-genre").hasAuthority("ROLE_ADMIN")
                .antMatchers("/api/reviewer/complete-profile","/api/reviewer/rate-movie").hasAuthority("ROLE_REVIEWER")
                .and()
                .httpBasic()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
