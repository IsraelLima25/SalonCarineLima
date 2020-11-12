package br.com.salon.carine.lima.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

public class PasswordDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Length(min = 8, message = "Senha inválida. A senha deve conter 8 caracteres alfanuméricos")
	private String senha;
	
	@Length(min = 8, message = "Senha inválida. A senha deve conter 8 caracteres alfanuméricos")
	private String confirmacaoSenha;
	
	public PasswordDTO() {
	}

	public PasswordDTO(String senha, String confirmacaoSenha) {
		super();
		this.senha = senha;
		this.confirmacaoSenha = confirmacaoSenha;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getConfirmacaoSenha() {
		return confirmacaoSenha;
	}

	public void setConfirmacaoSenha(String confirmacaoSenha) {
		this.confirmacaoSenha = confirmacaoSenha;
	}
}
