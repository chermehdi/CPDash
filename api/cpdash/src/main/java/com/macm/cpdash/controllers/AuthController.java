package com.macm.cpdash.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.macm.cpdash.domain.dto.ConnectionRequest;
import com.macm.cpdash.domain.dto.ErrorResponse;
import com.macm.cpdash.domain.dto.RegistrationRequest;
import com.macm.cpdash.services.AuthService;

/**
 * @author AEroui
 */
@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authenticationService;

    @Value("${app.domain.current}")
    private String root;

    Logger log = LoggerFactory.getLogger(getClass());

    /**
     * handle user login
     *
     * @param user   the login request
     * @param errors validation errors
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody ConnectionRequest user, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(new ErrorResponse(errors));
        }
        return authenticationService.authenticate(user);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> register(@Valid @RequestBody RegistrationRequest request, Errors errors,
                                      WebRequest webRequest) {
        if (errors.hasErrors()) {
            log.info("hit the signup");
            return ResponseEntity.badRequest().body(new ErrorResponse(errors));
        }
        return authenticationService.register(request, webRequest);
    }

}
