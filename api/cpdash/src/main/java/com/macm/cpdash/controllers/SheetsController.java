package com.macm.cpdash.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.macm.cpdash.errors.UnAuthorizedException;
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

	Logger log = LoggerFactory.getLogger(getClass());

	@GetMapping
	public ResponseEntity<List<Sheet>> getAllByUser(Authentication auth) {
		JwtUser userDetails = (JwtUser) auth.getPrincipal();
		UserEntity user = userRepository.getOne(userDetails.getId());
		return ResponseEntity.ok(user.getSheets().stream().map(Sheet::new).collect(Collectors.toList()));
	}

	@GetMapping(path = "/{sheetHash}")
	public ResponseEntity<Sheet> getById(@PathParam("sheetHash") String sheetHash) {
		return ResponseEntity.ok(new Sheet(sheetRepository.findBySheetHash(sheetHash)));
	}

	@PostMapping
	public ResponseEntity<Sheet> create(Authentication auth, @RequestBody Sheet sheet) {
		Sheet createdSheet = sheetService.create(sheet, auth);
		// TODO check for successful or failure
		log.info("hit the create sheet with " + sheet);
		return ResponseEntity.ok(createdSheet);
	}

	@PutMapping
	public ResponseEntity<Sheet> update(Authentication auth, @RequestBody Sheet sheet) {
		try {
			Sheet updatedSheet = sheetService.update(sheet, auth);
			return ResponseEntity.ok(updatedSheet);
		} catch (UnAuthorizedException exception) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

	@DeleteMapping(path = "/")
	public ResponseEntity<Void> delete(Authentication auth, @RequestBody Sheet sheet) {
		try {
			sheetService.delete(sheet, auth);
			return ResponseEntity.ok().build();
		} catch (UnAuthorizedException exception) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}
}
