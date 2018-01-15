package com.macm.cpdash.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.macm.cpdash.domain.dto.Profile;
import com.macm.cpdash.domain.entities.UserEntity;
import com.macm.cpdash.repositories.UserRepository;
import com.macm.cpdash.security.JwtUser;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping
	public ResponseEntity<Profile> getUserProfile(Authentication auth) {
		JwtUser userDetails = (JwtUser) auth.getPrincipal();
		UserEntity user = userRepository.getOne(userDetails.getId());

		return ResponseEntity.ok(new Profile(user.getProfile()));
	}

	@GetMapping(path = "/{user_id}")
	public ResponseEntity<Profile> getUserProfileById(@PathVariable("user_id") Long userId) {
		return ResponseEntity.ok(new Profile(userRepository.getOne(userId).getProfile()));
	}
	
	@PutMapping
	public ResponseEntity<Profile> updateUserProfile(Authentication auth, @RequestBody Profile profile) {
		// TODO check if its the profile Owner
		// TODO update Profile 
		return ResponseEntity.ok(profile);
	}
}
