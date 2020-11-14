package br.com.salon.carine.lima.converters;

import br.com.salon.carine.lima.dto.EnderecoDTO;
import br.com.salon.carine.lima.dto.UsuarioAlterarPerfilDTO;
import br.com.salon.carine.lima.dto.UsuarioDTO;
import br.com.salon.carine.lima.models.Endereco;
import br.com.salon.carine.lima.models.Usuario;

public class ConvertersUsuario {

	public static Usuario deUsuarioDTOParaUsuario(UsuarioDTO usuarioDTO) {

		Usuario usuario = new Usuario();
		usuario.setEmail(usuarioDTO.getEmail());

		Endereco endereco = ConvertersEndereco.deEnderecoDTOParaEndereco(usuarioDTO.getEndereco());

		usuario.setEndereco(endereco);
		usuario.setNome(usuarioDTO.getNome());
		usuario.setSenha(usuarioDTO.getPassword().getSenha());
		usuario.setSalt(usuarioDTO.getSalt());

		return usuario;

	}

	public static UsuarioAlterarPerfilDTO deUsuarioParaUsuarioAlterarPerfilDTO(Usuario usuario) {

		UsuarioAlterarPerfilDTO usuarioAlterarPerfilDTO = new UsuarioAlterarPerfilDTO();
		usuarioAlterarPerfilDTO.setNome(usuario.getNome());
		usuarioAlterarPerfilDTO.setEmail(usuario.getEmail());

		EnderecoDTO enderecoDTO = ConvertersEndereco.deEnderecoParaEnderecoDTO(usuario.getEndereco());

		usuarioAlterarPerfilDTO.setEndereco(enderecoDTO);

		return usuarioAlterarPerfilDTO;
	}

}
