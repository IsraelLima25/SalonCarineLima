package br.com.salon.carine.lima.dto;

public class ClienteDTO {

	private Integer id;
	private String nome;
	private String email;
	private String telefone;
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
