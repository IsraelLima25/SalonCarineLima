package br.com.salon.carine.lima.security;

import br.com.salon.carine.lima.models.Usuario;

public interface BcriptyHash {
	
	public Usuario usuarioEncripty (Usuario usuario);
	public String saltHash();
}
