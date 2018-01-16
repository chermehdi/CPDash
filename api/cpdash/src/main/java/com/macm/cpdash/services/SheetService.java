package com.macm.cpdash.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.macm.cpdash.domain.dto.Sheet;
import com.macm.cpdash.domain.entities.SheetEntity;
import com.macm.cpdash.domain.entities.UserEntity;
import com.macm.cpdash.errors.UnAuthorizedException;
import com.macm.cpdash.repositories.SheetRepository;

@Service
public class SheetService {

	@Autowired
	private AuthService authService;

	@Autowired
	private SheetRepository sheetRepository;

	public Sheet create(Sheet sheet, Authentication auth) {
		UserEntity userEntity = authService.getUserEntityFromAuth(auth);
		SheetEntity sheetEntity = new SheetEntity();
		sheetEntity.setOwner(userEntity);
		sheetEntity.setName(sheet.getName());
		sheetEntity.setDescription(sheet.getDescription());
		sheetEntity.setSheetHash(UUID.randomUUID().toString().substring(0, 20));
		sheetEntity = sheetRepository.save(sheetEntity);

		return new Sheet(sheetEntity);
	}

	public Sheet update(Sheet sheet, Authentication auth) throws UnAuthorizedException {
		SheetEntity sheetEntity = sheetRepository.findBySheetHash(sheet.getHash());
		UserEntity userEntity = authService.getUserEntityFromAuth(auth);

		// TODO throw specific exception
		if (!validateOnwer(sheetEntity, userEntity))
			throw new UnAuthorizedException("User not allowed to update Sheet!");

		sheetEntity.setName(sheet.getName());
		sheetEntity.setDescription(sheet.getDescription());

		sheetRepository.save(sheetEntity);
		return new Sheet(sheetEntity);
	}

	public void delete(Sheet sheet, Authentication auth) throws UnAuthorizedException {
		SheetEntity sheetEntity = sheetRepository.findBySheetHash(sheet.getHash());
		UserEntity userEntity = authService.getUserEntityFromAuth(auth);

		// TODO throw specific exception
		if (!validateOnwer(sheetEntity, userEntity))
			throw new UnAuthorizedException("User not allowed to delete Sheet!");

		sheetRepository.delete(sheetEntity);
	}

	private boolean validateOnwer(SheetEntity sheetEntity, UserEntity userEntity) {
		return sheetEntity.getOwner().getId().equals(userEntity.getId());
	}

}
