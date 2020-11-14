package br.com.salon.carine.lima.validations;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.salon.carine.lima.models.Usuario;
import br.com.salon.carine.lima.repositoriessdp.UsuarioRepositorySJPA;

public class EmailInsertAccountValidator implements 
	ConstraintValidator<EmailAccountValidator, String> {
	
	@Autowired
	private UsuarioRepositorySJPA usuarioRepository;
	
	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		
		Optional<Usuario> optionalUsuario = usuarioRepository.findByEmail(email);
		
		return optionalUsuario.isPresent();
	}

}
