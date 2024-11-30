package com.example.fitnesstracker.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import jakarta.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles;  // e.g., USER, ADMIN

    // Default constructor
    public User() {}

    // Constructor with parameters
    public User(String username, String password, String email, Set<String> roles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }

    // Getters and Setters for id, password, and email

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    // UserDetails interface methods

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> (GrantedAuthority) () -> "ROLE_" + role.toUpperCase())  // Prefix with "ROLE_"
                .toList();
    }


    @Override
    public String getUsername() {
        return username;  // No need to define this separately, using it from the interface
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;  // Can add logic if needed
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;  // Can add logic if needed
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // Can add logic if needed
    }

    @Override
    public boolean isEnabled() {
        return true;  // Can add logic for enabling/disabling user accounts
    }
}
