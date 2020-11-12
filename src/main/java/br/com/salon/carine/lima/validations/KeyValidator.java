package br.com.salon.carine.lima.validations;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.salon.carine.lima.repositoriessdp.RecoveryRepositorySJPA;

public class KeyValidator implements ConstraintValidator<KeyInsertValidator, String> {

	@Autowired
	private RecoveryRepositorySJPA recoveryRepository;
	
	@Override
	public boolean isValid(String chaveRecebida, ConstraintValidatorContext context) {
		
		Optional<String> chaveAcesso = recoveryRepository.findByChave(chaveRecebida);
		
		return chaveAcesso.isPresent();
		
	}
}
