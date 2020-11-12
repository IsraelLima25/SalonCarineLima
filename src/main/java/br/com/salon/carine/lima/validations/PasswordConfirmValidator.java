package br.com.salon.carine.lima.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.salon.carine.lima.dto.PasswordDTO;

public class PasswordConfirmValidator implements 
	ConstraintValidator<PasswordConfirmInsertValidator, PasswordDTO> {

	@Override
	public boolean isValid(PasswordDTO password, ConstraintValidatorContext context) {
		
		if(password.getSenha().equals(password.getConfirmacaoSenha())) {
			return true;
		}
		
		return false;
	}

}
