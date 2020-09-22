package br.com.salon.carine.lima.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class EnderecoDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer idEndereco;

	@Pattern(regexp = "(^$|.{8})", message = "cep inválido.")
	private String cep;

	@NotEmpty(message = "Bairro obrigatório")
	private String bairro;

	@Pattern(regexp = "(^$|.{4,})", message = "Logradouro inválido. Mínimo 5 caracteres")
	private String logradouro;
	
	private Integer numero;

	@Pattern(regexp = "(^$|.{4,})", message = "Complemento inválido. Mínimo 5 caracteres")
	private String complemento;

	@Pattern(regexp = "(^$|.{4,})", message = "Ponto referencia inválido. Mínimo 5 caractere")
	private String pontoReferencia;

	public EnderecoDTO() {

	}

	public EnderecoDTO(Integer idEndereco, String cep, String bairro, String logradouro, Integer numero,
			String complemento, String pontoReferencia) {

		this.idEndereco = idEndereco;
		this.cep = cep;
		this.bairro = bairro;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.pontoReferencia = pontoReferencia;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getPontoReferencia() {
		return pontoReferencia;
	}

	public void setPontoReferencia(String pontoReferencia) {
		this.pontoReferencia = pontoReferencia;
	}

	public Integer getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(Integer idEndereco) {
		this.idEndereco = idEndereco;
	}

}
