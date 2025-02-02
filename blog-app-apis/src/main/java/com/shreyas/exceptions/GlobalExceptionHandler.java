package com.shreyas.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(ResourceNotFound.class)
	public ResponseEntity<?> resourceNotFoundExceptionHandler(ResourceNotFound ex){
		String message = ex.getMessage();
		return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex){
		Map<String,String> mp = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(error->{
			String fieldName = ((FieldError) error).getField();
			String errorMsg = error.getDefaultMessage();
			mp.put(fieldName, errorMsg);
		});
		return new ResponseEntity<>(mp,HttpStatus.BAD_REQUEST);
	}
}
