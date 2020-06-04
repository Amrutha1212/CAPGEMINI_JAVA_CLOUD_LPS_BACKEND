package com.capgemini.lps.response;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleCustomValidationError(MethodArgumentNotValidException e) {
		Response<?> response = new Response(true, e.getBindingResult().getFieldError().getDefaultMessage(),
				new ArrayList());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

//	@Override
//	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
//		HttpHeaders headers, HttpStatus status, WebRequest reruest	){
//		List<FieldError> errors = ex.getBindingResult().getFieldErrors();
//		Map<String,String> fieldErrors = new HashMap<String, String>();
//		Response response = new Response();
//		for(FieldError fe : errors) {
//			fieldErrors.put(fe.getField(), fe.getDefaultMessage());
//		}
//		response.setFieldErrors(fieldErrors);
//		return new ResponseEntity<Object>(response,status.BAD_REQUEST);
//
//	}

}
