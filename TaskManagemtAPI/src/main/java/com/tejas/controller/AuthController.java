package com.tejas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tejas.config.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        
        // After successful authentication, generate a JWT token
        return jwtUtil.generateToken(authRequest.getUsername(), getRoleFromAuthentication(authentication));
    }

    private String getRoleFromAuthentication(Authentication authentication) {
        // Get the user's role from authentication
        return authentication.getAuthorities().stream()
                .map(Object::toString)
                .findFirst()
                .orElse("USER"); // Default role
    }
}

class AuthRequest {
    private String username;
    private String password;
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}
	public Object getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

    // Getters and Setters
}
