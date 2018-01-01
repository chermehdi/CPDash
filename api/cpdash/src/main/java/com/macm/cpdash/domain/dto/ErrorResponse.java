package com.macm.cpdash.domain.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.validation.Errors;

public class ErrorResponse {

	private List<String> errors;

	public ErrorResponse() {
		errors = new ArrayList<>();
	}

	public ErrorResponse(String error) {
		this.errors = Arrays.asList(error);
	}

	public ErrorResponse(List<String> errors) {
		this.errors = errors;
	}

	public ErrorResponse(Errors errors) {
		this.errors = errors.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	@Override
	public String toString() {
		return "ErrorResponse{" + "errors=" + errors + '}';
	}

}
