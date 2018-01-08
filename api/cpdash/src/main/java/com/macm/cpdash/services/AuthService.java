package com.macm.cpdash.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import com.macm.cpdash.domain.dto.ConnectionRequest;
import com.macm.cpdash.domain.dto.ErrorResponse;
import com.macm.cpdash.domain.dto.RegistrationRequest;
import com.macm.cpdash.domain.dto.TokenResponse;
import com.macm.cpdash.domain.dto.User;
import com.macm.cpdash.domain.entities.ProfileEntity;
import com.macm.cpdash.domain.entities.UserEntity;
import com.macm.cpdash.domain.factories.SuccessResponse;
import com.macm.cpdash.domain.factories.UserFactory;
import com.macm.cpdash.repositories.ProfileRepository;
import com.macm.cpdash.repositories.UserRepository;
import com.macm.cpdash.security.JwtUtils;
import com.macm.cpdash.services.events.OnRegistrationCompleteEvent;

/**
 * 
 * @author AEroui
 *
 */
@Service
public class AuthService {
	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProfileRepository profileRepository;
	
	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private UserFactory userFactory;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	ApplicationEventPublisher eventPublisher;

	public ResponseEntity<?> authenticate(ConnectionRequest userConnectionRequest) {

		final Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(
				userConnectionRequest.getEmail(), userConnectionRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(auth);
		String token = jwtUtils.generateToken(userConnectionRequest.getEmail());
		UserEntity currentUser = userRepository.findByEmail(userConnectionRequest.getEmail());
		if (currentUser == null) {
			return ResponseEntity.badRequest().body(new ErrorResponse(
					Arrays.asList("Credentials don't match, please make sure you entered the correct informations")));
		}

		User user = new User(currentUser);
		return ResponseEntity.ok(new TokenResponse(token, user));
	}

	public ResponseEntity<?> register(RegistrationRequest request, WebRequest webRequest) {
		UserEntity userEntity = userRepository.findByEmail(request.getEmail());
		if (userEntity != null) {
			return ResponseEntity.badRequest()
					.body(new ErrorResponse(Arrays.asList("User already exists with same email")));
		}

		if (!request.getPassword().equals(request.getPasswordConfirmation())) {
			return ResponseEntity.badRequest()
					.body(new ErrorResponse(Arrays.asList("Password and Password confirmation don't match ")));
		}

		userEntity = userFactory.from(request);
		String appUrl = webRequest.getContextPath();
		userEntity = userRepository.save(userEntity);
		addDefaultProfile(userEntity);
		eventPublisher.publishEvent(new OnRegistrationCompleteEvent(userEntity, webRequest.getLocale(), appUrl));
		return ResponseEntity.ok(new SuccessResponse(true, "User created Successfully"));
	}

	private void addDefaultProfile(UserEntity userEntity) {
		ProfileEntity profileEntity = new ProfileEntity();
		profileEntity.setUser(userEntity);
		profileEntity.setPicture("https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png");
		
		profileRepository.save(profileEntity);
		userEntity.setProfile(profileEntity);
	}

}
