package br.com.salon.carine.lima.dto;

import br.com.salon.carine.lima.models.Endereco;
import br.com.salon.carine.lima.util.Message;

public class ClienteDTO {

	private Integer id;
	private String nome;
	private String email;
	private String telefone;
	private Endereco endereco;
	private Message mensagem;

	public ClienteDTO() {

	}

	public ClienteDTO(Integer id) {
		this.id = id;
	}

	public ClienteDTO(Integer id, String nome, String email, String telefone, Endereco endereco) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.endereco = endereco;
	}

	public ClienteDTO(String nome, String email, String telefone, Endereco endereco) {
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

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public Message getMensagem() {
		return mensagem;
	}
	
	public void setMensagem(Message mensagem) {
		this.mensagem = mensagem;
	}

	@Override
	public String toString() {
		return "ClienteDTO [nome=" + nome + ", email=" + email + ", telefone=" + telefone + ", endereco=" + endereco
				+ "]";
	}

}
