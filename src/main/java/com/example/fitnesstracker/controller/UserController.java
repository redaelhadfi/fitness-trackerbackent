package com.example.fitnesstracker.controller;

import com.example.fitnesstracker.entity.User;
import com.example.fitnesstracker.security.JwtUtil;
import com.example.fitnesstracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;  // <-- Import the Map class
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    // User registration
    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        if (user.getRoles() == null || user.getRoles().isEmpty()) {

            user.setRoles(Set.of("USER"));
        }
        userService.registerUser(user);
        return "User registered successfully!";
    }

    // User login (authentication) using JSON body
    @PostMapping("/authenticate")
    public String authenticate(@RequestBody Map<String, String> credentials) throws Exception {
        String username = credentials.get("username");
        String password = credentials.get("password");

        try {
            // Authenticate the user
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
        } catch (Exception e) {
            throw new Exception("Invalid username or password", e);
        }

        // Generate and return the JWT token
        final UserDetails userDetails = userService.loadUserByUsername(username);
        return jwtUtil.generateToken(userDetails);
    }

    // Fetch user profile
    @GetMapping("/profile")
    public User getUserProfile() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.findByUsername(username);
    }


}
