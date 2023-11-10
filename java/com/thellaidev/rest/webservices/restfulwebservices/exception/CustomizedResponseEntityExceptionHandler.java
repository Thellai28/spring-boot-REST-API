package com.thellaidev.rest.webservices.restfulwebservices.exception;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.thellaidev.rest.webservices.restfulwebservices.user.UserNotFoundException;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler( Exception.class )
	public  ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request)throws Exception  {
		ErrorDetails errorDetails = new ErrorDetails(
				LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler( UserNotFoundException.class )
	public  ResponseEntity<ErrorDetails> handleUserNotFoundException(Exception ex, WebRequest request)throws Exception  {
		ErrorDetails errorDetails = new ErrorDetails(
				LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@Override 
	// Since this  method is not final, we can overRIde the actual method, so we don't need to specify
	// the @ExceptionHandler() here and we should not change the method signatures.
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
										MethodArgumentNotValidException ex, 
										HttpHeaders headers, HttpStatusCode status,
										WebRequest request) {
		
		// Collect all the errors and show then in response body : 
		int totalErrorCount = ex.getErrorCount();
		int errorCount = 1;
		StringBuilder AllErrorsInSigleString = new StringBuilder( "Error count : " + totalErrorCount + " " );
		List<FieldError> AllErrorsAsList = ex.getFieldErrors();
		
		for( FieldError error : AllErrorsAsList) {
			AllErrorsInSigleString.append( "{ Error no : " + errorCount++ + "->" );
			AllErrorsInSigleString.append( error.getDefaultMessage() ).append("} ");
		}
		
		// Show only one error message : 
		String showOnlyOneErrorMessage = ex.getFieldError().getDefaultMessage();
		
		
		// Creating ErrorDeatils object : 
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), AllErrorsInSigleString + "",
										request.getDescription(false));
		
		return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
	}
}
