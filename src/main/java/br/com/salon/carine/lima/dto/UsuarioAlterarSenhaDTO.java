package br.com.salon.carine.lima.dto;

import java.io.Serializable;

import br.com.salon.carine.lima.validations.PasswordAtualValidator;

public class UsuarioAlterarSenhaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@PasswordAtualValidator
	private String senhaAtual;

//	@PasswordConfirmInsertValidator
	private PasswordDTO passwordDTO;

	public UsuarioAlterarSenhaDTO() {

	}

	public UsuarioAlterarSenhaDTO(String senhaAtual, PasswordDTO passwordDTO) {
		this.passwordDTO = passwordDTO;
		this.senhaAtual = senhaAtual;
	}

	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

	public PasswordDTO getPasswordDTO() {
		return passwordDTO;
	}

	public void setPasswordDTO(PasswordDTO passwordDTO) {
		this.passwordDTO = passwordDTO;
	}

}
