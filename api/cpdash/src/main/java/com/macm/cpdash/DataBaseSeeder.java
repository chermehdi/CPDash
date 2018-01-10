package com.macm.cpdash;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.macm.cpdash.domain.entities.UserEntity;
import com.macm.cpdash.repositories.UserRepository;

@Component
public class DataBaseSeeder implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;

	@Override
	public void run(String... arg0) throws Exception {
		UserEntity current = new UserEntity();
		current.setUsername("cpdashAdmin");
		current.setPassword(encoder.encode("123456"));
		current.setEmail("user@cpdash.com");
		current.setEnabled(true);
		current.setCreationDate(new Date());
		current.setUpdateDate(new Date());
		current.setLastPasswordResetDate(new Date());

		userRepository.save(current);
	}

}
