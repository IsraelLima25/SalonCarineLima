package br.com.salon.carine.lima.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.salon.carine.lima.converters.ConvertersUsuario;
import br.com.salon.carine.lima.dto.UsuarioDTO;
import br.com.salon.carine.lima.models.Role;
import br.com.salon.carine.lima.models.Usuario;
import br.com.salon.carine.lima.repositoriessdp.UsuarioRepository;

@Service
public class LoginService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	public void registrarUsuarioService(UsuarioDTO usuarioDTO) {
		
		Usuario usuario = ConvertersUsuario
				.deUsuarioDTOParaUsuario(usuarioDTO);
		usuario.getRoles().add(getRole());
		
		usuarioRepository.save(usuario);
	}

	private Role getRole() {
		Role role = new Role("'ROLE_ADM'");
		return role;
	}
	
}
