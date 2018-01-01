package com.macm.cpdash.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.macm.cpdash.domain.dto.User;
import com.macm.cpdash.domain.entities.UserEntity;
import com.macm.cpdash.repositories.UserRepository;

/**
 * 
 * @author AEroui
 *
 * implementation fo the UserDetailsService to fetch a given user by it's
 * username, (in our case email)
 */
@Service
public class JwtUserDetails implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	Logger log = LoggerFactory.getLogger(JwtUserDetails.class);

	/**
	 * Load a given user by it's email, or throw and exception
	 */
	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByEmail(s);
		if (userEntity == null) {
			throw new UsernameNotFoundException("could not find user with given user email address ");
		} else {
			return JwtFactory.createUser(new User(userEntity));
		}
	}
}
