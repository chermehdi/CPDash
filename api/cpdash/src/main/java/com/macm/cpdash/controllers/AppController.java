package com.macm.cpdash.controllers;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.macm.cpdash.domain.entities.ProfileEntity;

@RestController
@RequestMapping("/api/app")
public class AppController {

	@GetMapping("/validate")
	public ProfileEntity validate(HttpServletRequest request) {
		return null;
	}

}
