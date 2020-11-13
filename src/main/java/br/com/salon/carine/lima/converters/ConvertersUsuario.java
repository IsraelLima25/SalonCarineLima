package br.com.salon.carine.lima.converters;

import br.com.salon.carine.lima.dto.UsuarioDTO;
import br.com.salon.carine.lima.models.Endereco;
import br.com.salon.carine.lima.models.Usuario;

public class ConvertersUsuario {
	
	public static Usuario deUsuarioDTOParaUsuario(UsuarioDTO usuarioDTO) {
		
		Usuario usuario = new Usuario();
		usuario.setEmail(usuarioDTO.getEmail());
		
		Endereco endereco = ConvertersEndereco
				.deEnderecoDTOParaEndereco(usuarioDTO.getEndereco());
		
		usuario.setEndereco(endereco);
		usuario.setNome(usuarioDTO.getNome());
		usuario.setSenha(usuarioDTO.getPassword().getSenha());
		usuario.setSalt(usuarioDTO.getSalt());
		
		return usuario;
		
	}

}
