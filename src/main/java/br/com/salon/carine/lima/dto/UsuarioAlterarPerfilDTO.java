package br.com.salon.carine.lima.dto;

import java.io.Serializable;

import javax.validation.Valid;

import org.hibernate.validator.constraints.Length;

public class UsuarioAlterarPerfilDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Length(min = 10, max = 20, message = "Nome inválido. Mínimo 10 e máximo de 20 caracteres.")
	private String nome;

	private String email;

	@Valid
	private EnderecoDTO endereco;

	public UsuarioAlterarPerfilDTO() {
		
	}
	
	public UsuarioAlterarPerfilDTO(String nome, String email, EnderecoDTO endereco) {
		super();
		this.nome = nome;
		this.email = email;
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

	public EnderecoDTO getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoDTO endereco) {
		this.endereco = endereco;
	}

}
