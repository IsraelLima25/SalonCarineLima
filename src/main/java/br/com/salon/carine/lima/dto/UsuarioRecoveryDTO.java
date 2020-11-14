package br.com.salon.carine.lima.dto;

import java.io.Serializable;

import br.com.salon.carine.lima.validations.EmailAccountValidator;

public class UsuarioRecoveryDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EmailAccountValidator
	private String email;

	public UsuarioRecoveryDTO() {

	}

	public UsuarioRecoveryDTO(String email) {
		super();
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
