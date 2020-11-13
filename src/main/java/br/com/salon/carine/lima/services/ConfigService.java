package br.com.salon.carine.lima.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.salon.carine.lima.models.Usuario;
import br.com.salon.carine.lima.repositoriessdp.UsuarioRepositorySJPA;
import br.com.salon.carine.lima.security.AuthenticationFacade;
import br.com.salon.carine.lima.security.BCriptyHashImpl;

@Service
public class ConfigService {

	@Autowired
	private UsuarioRepositorySJPA usuarioRepository;
	
	@Autowired
	private AuthenticationFacade userAuthentication;
	
	@Autowired
	private BCriptyHashImpl bCriptyHashImpl;
	
	public void alterarSenha(String novaSenha) {
		
		Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(userAuthentication
				.getAuthentication().getName());
		
		if(usuarioOptional.isPresent()) {
			Usuario usuario = usuarioOptional.get();
			usuario.setSenha(novaSenha);
			bCriptyHashImpl.usuarioEncripty(usuario);
			
			usuarioRepository.save(usuario);
		}
	}
}
