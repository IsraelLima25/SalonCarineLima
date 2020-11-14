package br.com.salon.carine.lima.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.salon.carine.lima.converters.ConvertersEndereco;
import br.com.salon.carine.lima.dto.UsuarioAlterarPerfilDTO;
import br.com.salon.carine.lima.models.Endereco;
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

		Usuario usuario = userAuthenticated();
		usuario.setSenha(novaSenha);
		bCriptyHashImpl.usuarioEncripty(usuario);

		usuarioRepository.save(usuario);
	}

	public Usuario userAuthenticated() {

		Optional<Usuario> usuarioOptional = usuarioRepository
				.findByEmail(userAuthentication.getAuthentication().getName());

		if (usuarioOptional.isPresent()) {
			return usuarioOptional.get();
		}

		return null;
	}

	public void alterarPerfil(UsuarioAlterarPerfilDTO usuarioDTO) {
		Usuario usuarioAutenticado = userAuthenticated();
		usuarioAutenticado.setNome(usuarioDTO.getNome());
		Endereco endereco = ConvertersEndereco.deEnderecoDTOParaEndereco(usuarioDTO.getEndereco());
		usuarioAutenticado.setEndereco(endereco);
		
		usuarioRepository.save(usuarioAutenticado);
		
	}
}
