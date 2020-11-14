package br.com.salon.carine.lima.security;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import br.com.salon.carine.lima.models.Usuario;

@Component
public class BCriptyHashImpl implements BcriptyHash {

	@Override
	public Usuario usuarioEncripty(Usuario usuario) {
		String salt = saltHash();
		usuario.setSenha(BCrypt.hashpw(usuario.getSenha(), salt));
		usuario.setSalt(salt);
		
		return usuario;
	}

	@Override
	public String saltHash() {
		
	    String salGerado = BCrypt.gensalt();
	    //String senhaHasheada = BCrypt.hashpw(senha, salGerado);
	    return salGerado;
	}
}
