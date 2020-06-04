package com.capgemini.lps.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.capgemini.lps.entity.Applicant;
import com.capgemini.lps.entity.Loan;
import com.capgemini.lps.entity.User;
import com.capgemini.lps.response.Response;

@ControllerAdvice
public class GenericExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<Response<Applicant>> handleException(ApplicantNotFoundException ex) {
		Response<Applicant> response = new Response<>(true, ex.getMessage(), null);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@ExceptionHandler
	public ResponseEntity<Response<Loan>> handleException(LoanProgNotFoundException ex) {
		Response<Loan> response = new Response<>(true, ex.getMessage(), null);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@ExceptionHandler
	public ResponseEntity<Response<User>> handleException(UserNotFoundException ex) {
		Response<User> response = new Response<>(true, ex.getMessage(), null);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@ExceptionHandler
	public ResponseEntity<Response<User>> handleException(EmailNotFoundException ex) {
		Response<User> response = new Response<>(true, ex.getMessage(), null);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
