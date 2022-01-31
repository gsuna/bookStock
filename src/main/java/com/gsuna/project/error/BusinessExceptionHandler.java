package com.gsuna.project.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.gsuna.project.exception.BusinessException;

@ControllerAdvice
public class BusinessExceptionHandler {

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ApiError> handleBusinesException(BusinessException exception,WebRequest request){
		ApiError apiError = new ApiError(exception.getCode(),exception.getMessage(),request.getDescription(false));
		return new ResponseEntity<>(apiError,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<ApiError> handleRuntimeException(UsernameNotFoundException exception,WebRequest request){
		ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED.toString(),exception.getMessage(),request.getDescription(false));
		return new ResponseEntity<>(apiError,HttpStatus.UNAUTHORIZED);
	}
}
