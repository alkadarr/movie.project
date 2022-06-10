package com.Movies.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Username", nullable = false, length = 50)
    private String username;

    @Column(name = "Password", nullable = false, length = 200)
    private String password;

    @Column(name = "Role", nullable = false, length = 20)
    private String role;

    @Column(name = "Enabled", nullable = false)
    private boolean enabled;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "account")
    private Reviewer reviewer;

    public Account(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.enabled =  true;
    }
}