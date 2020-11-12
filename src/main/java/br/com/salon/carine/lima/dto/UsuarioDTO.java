package br.com.salon.carine.lima.dto;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import br.com.salon.carine.lima.validations.KeyInsertValidator;
import br.com.salon.carine.lima.validations.PasswordConfirmInsertValidator;

public class UsuarioDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Length(min = 10, max = 30, message = "Nome inválido")
	private String nome;

	@NotBlank(message = "Email obrigatório")
	@Email(message = "Email inválido")
	private String email;

	@Valid
	private EnderecoDTO endereco;

	@Valid
	@PasswordConfirmInsertValidator
	private PasswordDTO password;
	
	@KeyInsertValidator
	private String chave;

	public UsuarioDTO() {

	}

	public UsuarioDTO(String nome, String email, String senha, EnderecoDTO endereco, String senhaConfirmada,
			String chave) {
		this.nome = nome;
		this.email = email;
		this.endereco = endereco;
		this.chave = chave;
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

	public PasswordDTO getPassword() {
		return password;
	}

	public void setPassword(PasswordDTO password) {
		this.password = password;
	}

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

}
