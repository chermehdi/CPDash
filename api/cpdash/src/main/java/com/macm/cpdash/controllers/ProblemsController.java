package com.macm.cpdash.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.macm.cpdash.domain.dto.Problem;
import com.macm.cpdash.domain.dto.Sheet;

@RestController
@RequestMapping("/api/problem")
public class ProblemsController {

	@PostMapping("/")
	public ResponseEntity<Problem> create(Authentication auth, @RequestBody Sheet Problem)  {
		// TODO implement VIA a SERVICE
		return ResponseEntity.ok(null);
	}
	
	
}
