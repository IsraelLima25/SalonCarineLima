package br.com.salon.carine.lima.dto;

public class EnderecoDTO {

	private String cep;
	private String bairro;
	private String logradouro;
	private Integer numero;
	private String complemento;
	private String pontoReferencia;

	public EnderecoDTO() {

	}

	public EnderecoDTO(String cep, String bairro, String logradouro, Integer numero, String complemento,
			String pontoReferencia) {
		super();
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

	@Override
	public String toString() {
		return "EnderecoDTO [cep=" + cep + ", bairro=" + bairro + ", logradouro=" + logradouro + ", numero=" + numero
				+ ", complemento=" + complemento + ", pontoReferencia=" + pontoReferencia + "]";
	}

}
