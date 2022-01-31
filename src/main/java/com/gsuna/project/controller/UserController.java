package com.gsuna.project.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gsuna.project.entity.User;
import com.gsuna.project.error.ApiError;
import com.gsuna.project.service.UserService;
import com.gsuna.project.util.GenericResponse;

@RestController
public class UserController {
	
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/1.0/users")
	public GenericResponse createUser(@Valid @RequestBody User user) {
		userService.save(user);
		return new GenericResponse("User created");
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiError handleValidationError(MethodArgumentNotValidException exception) {
		ApiError error = new ApiError("400", "Validation error", "/api/1.0/users");
		Map<String, String> validationErrors = new HashMap<>();
		exception.getBindingResult().getFieldErrors().forEach(e->validationErrors.put(e.getField(), e.getDefaultMessage()));
		error.setValidationErrors(validationErrors);
		return error;
	}
}
