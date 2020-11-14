package br.com.salon.carine.lima.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.salon.carine.lima.models.Usuario;
import br.com.salon.carine.lima.repositoriessdp.UsuarioRepositorySJPA;

@Service
public class UsuarioService implements UserDetailsService{
	
	public static String emailPage;
	
	@Autowired
	private UsuarioRepositorySJPA usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		emailPage = email;
		
		Optional<Usuario> optionalUsuario = usuarioRepository.findById(email);
		
		if(optionalUsuario.isPresent()) {
			return optionalUsuario.get();
		}
		
		throw new UsernameNotFoundException("O usuário" + email + "não foi encontrado");
	}
	
	public Optional<Usuario> userSaltByUser(String salt) {
		return usuarioRepository.findBySalt(salt);
	}
	
}
