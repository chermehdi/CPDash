package com.macm.cpdash.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.macm.cpdash.domain.dto.Sheet;
import com.macm.cpdash.domain.entities.UserEntity;
import com.macm.cpdash.repositories.SheetRepository;
import com.macm.cpdash.repositories.UserRepository;
import com.macm.cpdash.security.JwtUser;
import com.macm.cpdash.services.SheetService;

@RestController
@RequestMapping("/api/sheets")
public class SheetsController {

	@Autowired
	private SheetRepository sheetRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SheetService sheetService;

	@GetMapping(path = "/")
	public ResponseEntity<List<Sheet>> getAllByUser(Authentication auth) {
		JwtUser userDetails = (JwtUser) auth.getPrincipal();
		UserEntity user = userRepository.getOne(userDetails.getId());
		return ResponseEntity.ok(user.getSheets().stream().map(Sheet::new).collect(Collectors.toList()));
	}

	@GetMapping(path = "/{sheetHash}")
	public ResponseEntity<Sheet> getById(@PathParam("sheetHash") String sheetHash) {
		return ResponseEntity.ok(new Sheet(sheetRepository.findBySheetHash(sheetHash)));
	}

	@PostMapping(path = "/")
	public ResponseEntity<Sheet> create(Authentication auth, @RequestBody Sheet sheet) {
		Sheet createdSheet = sheetService.create(sheet, auth);
		// TODO check for successful or failure
		return ResponseEntity.ok(createdSheet);
	}

	@PutMapping(path = "/")
	public ResponseEntity<Sheet> update(Authentication auth, @RequestBody Sheet sheet) {
		Sheet updatedSheet = sheetService.update(sheet, auth);
		if (updatedSheet == null)
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

		return ResponseEntity.ok(updatedSheet);
	}

	@DeleteMapping(path = "/")
	public ResponseEntity<Void> delete(Authentication auth, @RequestBody Sheet sheet) {
		if (!sheetService.delete(sheet, auth))
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		return ResponseEntity.ok().build();
	}
}
