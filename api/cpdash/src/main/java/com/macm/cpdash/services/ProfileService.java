package com.macm.cpdash.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.macm.cpdash.repositories.ProfileRepository;

@Service
public class ProfileService {
	@Autowired
	private AuthService authService;
	
	@Autowired
	private ProfileRepository profileRepository;
	
	
}
