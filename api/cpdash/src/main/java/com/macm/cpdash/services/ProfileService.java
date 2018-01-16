package com.macm.cpdash.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.macm.cpdash.domain.dto.Profile;
import com.macm.cpdash.domain.entities.ProfileEntity;
import com.macm.cpdash.domain.entities.UserEntity;
import com.macm.cpdash.errors.UnAuthorizedException;
import com.macm.cpdash.repositories.ProfileRepository;
import com.macm.cpdash.repositories.UserRepository;
import com.macm.cpdash.security.JwtUser;

@Service
public class ProfileService {

	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
	private UserRepository userRepository;

	public Profile updateProfile(Profile profile, Authentication auth) throws UnAuthorizedException {
		JwtUser userDetails = (JwtUser) auth.getPrincipal();
		UserEntity user = userRepository.getOne(userDetails.getId());
		// TODO check if not authorized (if yes throw Exception)

		ProfileEntity profileEntity = user.getProfile();
		profileEntity.setFirstName(profile.getFirstName());
		profileEntity.setLastName(profile.getLastName());
		profileEntity.setBirthDate(profile.getBirthDate());
		profileEntity.setCity(profile.getCity());
		profileEntity.setCountry(profile.getCountry());
		profileEntity.setPicture(profile.getPicture());

		profileRepository.save(profileEntity);
		return new Profile(profileEntity);
	}

}
