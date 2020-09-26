package br.com.salon.carine.lima.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler(ArgumentNotValidException.class)
	public ResponseEntity<ValidationError> argumentNotValid(ArgumentNotValidException e, 
			HttpServletRequest request) {

		ValidationError err = new ValidationError(System.currentTimeMillis(), 
				HttpStatus.UNPROCESSABLE_ENTITY.value(),
				"Erro na validação dos campos", e.getMessage(), 
				request.getRequestURI());

		for (FieldError error : e.getResult().getFieldErrors()) {
			err.addError(error.getField(), 
			error.getDefaultMessage());
		}

		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);

	}
}
