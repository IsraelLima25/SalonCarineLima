package br.com.salon.carine.lima.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.Min;

public class MarcarAtendimentoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Min(value = 1, message = "cliente obrigatório")
	private Integer cliente;
	
	private BigDecimal desconto;
	private BigDecimal taxa;
	
	private String formaPagamento;
	
	private String bandeiraCartao;
	private Integer quantidadeParcelas;
	
	private String tipoEndereco;
	
	private String data;
	
	private EnderecoDTO endereco;
	
	private String hora;

	public MarcarAtendimentoDTO() {
	}

	public MarcarAtendimentoDTO(Integer cliente, BigDecimal desconto, BigDecimal taxa, String formaPagamento,
			String bandeiraCartao, Integer quantidadeParcelas, EnderecoDTO endereco, String tipoEndereco, String data,
			String hora) {

		this.cliente = cliente;
		this.desconto = desconto;
		this.taxa = taxa;
		this.formaPagamento = formaPagamento;
		this.bandeiraCartao = bandeiraCartao;
		this.quantidadeParcelas = quantidadeParcelas;
		this.tipoEndereco = tipoEndereco;
		this.endereco = endereco;
		this.data = data;
		this.hora = hora;
	}

	public Integer getCliente() {
		return cliente;
	}

	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}

	public BigDecimal getDesconto() {
		return desconto;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}

	public BigDecimal getTaxa() {
		return taxa;
	}

	public void setTaxa(BigDecimal taxa) {
		this.taxa = taxa;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
//		this.formaPagamento = FormaPagamento.getFormaPagamento(formaPagamento);
		this.formaPagamento = formaPagamento;
	}

	public String getTipoEndereco() {
		return tipoEndereco;
	}

	public void setTipoEndereco(String tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
	}

	public String getBandeiraCartao() {
		return bandeiraCartao;
	}

	public void setBandeiraCartao(String bandeira) {
		this.bandeiraCartao = bandeira;
	}

	public Integer getQuantidadeParcelas() {
		return quantidadeParcelas;
	}

	public void setQuantidadeParcelas(Integer quantidadeParcelas) {
		this.quantidadeParcelas = quantidadeParcelas;
	}

	public EnderecoDTO getEnderecoDTO() {
		return endereco;
	}

	public void setEnderecoDTO(EnderecoDTO endereco) {
		this.endereco = endereco;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

}
