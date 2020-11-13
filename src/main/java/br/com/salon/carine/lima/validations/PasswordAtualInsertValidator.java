package br.com.salon.carine.lima.validations;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;

import br.com.salon.carine.lima.models.Usuario;
import br.com.salon.carine.lima.repositoriessdp.UsuarioRepositorySJPA;
import br.com.salon.carine.lima.security.IAuthenticationFacade;

public class PasswordAtualInsertValidator implements 
	ConstraintValidator<PasswordAtualValidator, String> {
	
	@Autowired
	private IAuthenticationFacade authentication;
	
	@Autowired
	private UsuarioRepositorySJPA usuarioRepository;
	
	@Override
	public boolean isValid(String senha, ConstraintValidatorContext context) {
		
		Optional<Usuario> usuarioLogado = usuarioRepository
				.findByEmail(authentication.getAuthentication().getName());
		
		String senhaCodificada = BCrypt.hashpw(senha, usuarioLogado.get().getSalt());
		
		return senhaCodificada.equals(usuarioLogado.get().getSenha());
	}
}
