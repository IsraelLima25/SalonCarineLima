package br.com.salon.carine.lima.dto;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	@Length(min = 1, max = 25, message = "Nome Inválido. Mínimo 1 máximo 25 caracteres")
	private String nome;

	@Email(message = "Email inválido")
	private String email;

	@Pattern(regexp = "(^$|.{11})", message = "Telefone inválido. Formato (ddd) 9 99999999")
	private String telefone;
	
	@Valid
	private EnderecoDTO endereco;

	public ClienteDTO() {

	}

	public ClienteDTO(Integer id) {
		this.id = id;
	}

	public ClienteDTO(Integer id, String nome, String email, String telefone, EnderecoDTO endereco) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.endereco = endereco;
	}

	public ClienteDTO(String nome, String email, String telefone, EnderecoDTO endereco) {
		super();
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.endereco = endereco;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public EnderecoDTO getEndereco() {
		return endereco;
	}
	
	public void setEndereco(EnderecoDTO endereco) {
		this.endereco = endereco;
	}
}
