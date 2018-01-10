package com.macm.cpdash.controllers;

import com.macm.cpdash.domain.entities.UserEntity;
import com.macm.cpdash.repositories.UserRepository;
import com.macm.cpdash.security.JwtUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/me")
    public ResponseEntity<?> currentUser(Authentication auth) {
        JwtUser userDetails = (JwtUser) auth.getPrincipal();
        String currentUserEmail = userDetails.getEmail();
        log.info("current email " + currentUserEmail);
        UserEntity currentUser = userRepository.findByEmail(currentUserEmail);
        return ResponseEntity.ok(currentUser);
    }

    /**
     * check if the user sending the request is logged in, i.e the token is not tampered with
     * returns 200 status if their is an authentication in the security context, 400 otherwise
     */
    @GetMapping("/validity")
    public ResponseEntity<?> checkTokenValidity(Authentication auth) {
        JwtUser userDetails = (JwtUser) auth.getPrincipal();
        if (userDetails == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }
    
    
}
