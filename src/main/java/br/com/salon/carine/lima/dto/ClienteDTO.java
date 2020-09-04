package br.com.salon.carine.lima.dto;

import br.com.salon.carine.lima.models.Endereco;

public class ClienteDTO {

	private String nome;
	private String email;
	private String telefone;
	private Endereco endereco;

	public ClienteDTO() {

	}

	public ClienteDTO(String nome, String email, String telefone, Endereco endereco) {
		super();
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Override
	public String toString() {
		return "ClienteDTO [nome=" + nome + ", email=" + email + ", telefone=" + telefone + ", endereco=" + endereco
				+ "]";
	}

}
