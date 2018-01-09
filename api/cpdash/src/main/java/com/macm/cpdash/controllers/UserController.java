package com.macm.cpdash.controllers;

import com.macm.cpdash.domain.entities.UserEntity;
import com.macm.cpdash.repositories.UserRepository;
import com.macm.cpdash.security.JwtUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    Logger log = LoggerFactory.getLogger(getClass());

    @GetMapping("/me")
    public ResponseEntity<?> currentUser(Authentication auth) {
        JwtUser userDetails = (JwtUser) auth.getPrincipal();
        String currentUserEmail = userDetails.getEmail();
        log.info("current email " + currentUserEmail);
        UserEntity currentUser = userRepository.findByEmail(currentUserEmail);
        return ResponseEntity.ok(currentUser);
    }
}
