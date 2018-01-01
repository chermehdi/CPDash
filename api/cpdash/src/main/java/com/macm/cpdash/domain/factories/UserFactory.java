package com.macm.cpdash.domain.factories;

import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.macm.cpdash.domain.dto.RegistrationRequest;
import com.macm.cpdash.domain.entities.AuthorityEntity;
import com.macm.cpdash.domain.entities.AuthorityName;
import com.macm.cpdash.domain.entities.UserEntity;
import com.macm.cpdash.repositories.AuthorityRepository;

@Component
public class UserFactory {

	@Autowired
	AuthorityRepository authorityRepository;

	@Autowired
	PasswordEncoder encoder;

	/**
	 * create a User Object from a Registration request, (no validation is
	 * performed)
	 *
	 * @param request
	 *            the dto object
	 * @return the newly created User model
	 */
	public UserEntity from(RegistrationRequest request) {
		UserEntity current = new UserEntity();
		current.setUsername(request.getUsername());
		current.setPassword(encoder.encode(request.getPassword()));
		current.setEmail(request.getEmail());
		current.setEnabled(true);
		current.setCreationDate(new Date());
		current.setUpdateDate(new Date());
		current.setLastPasswordResetDate(new Date());
		current.setAuthorities(getAuthorities());

		return current;
	}

	private Set<AuthorityEntity> getAuthorities() {
		Set<AuthorityEntity> ret = new TreeSet<>();
		AuthorityEntity authority = authorityRepository.findByName(AuthorityName.ROLE_USER);
		if(authority != null)
			ret.add(authority);
		return ret;
	}
}
