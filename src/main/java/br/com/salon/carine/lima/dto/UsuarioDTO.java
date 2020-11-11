package br.com.salon.carine.lima.dto;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class UsuarioDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Length(min = 10, max = 30, message = "Nome inválido")
	private String nome;
	
	@NotBlank(message = "Email obrigatório")
	
	@Email(message = "Email inválido")
	private String email;
	
	@Valid
	private EnderecoDTO endereco;
	
	private String senha;
	
	private String senhaConfirmada;

	public UsuarioDTO() {

	}

	public UsuarioDTO(String nome, String email, String senha, EnderecoDTO endereco,
			String senhaConfirmada) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.endereco = endereco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public EnderecoDTO getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoDTO endereco) {
		this.endereco = endereco;
	}

	public String getSenhaConfirmada() {
		return senhaConfirmada;
	}

	public void setSenhaConfirmada(String senhaConfirmada) {
		this.senhaConfirmada = senhaConfirmada;
	}
}
