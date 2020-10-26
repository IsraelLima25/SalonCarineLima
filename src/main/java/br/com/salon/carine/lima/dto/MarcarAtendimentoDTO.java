package br.com.salon.carine.lima.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.salon.carine.lima.validations.DateInsert;

public class MarcarAtendimentoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	@Min(value = 1, message = "cliente obrigatório")
	private Integer cliente;

	private BigDecimal desconto;
	private BigDecimal taxa;

	@NotBlank(message = "Pagamento obrigatório")
	private String formaPagamento;

	@NotBlank(message = "Bandeira obrigatório")
	private String bandeiraCartao;

	@NotNull(message = "Quantidade parcela obrigatório")
	@Min(value = 1, message = "Quantidade parcela inválida")
	private Integer quantidadeParcelas;

	@NotBlank(message = "Endereco obrigatório")
	private String tipoEndereco;
	
	@DateInsert
	@NotBlank(message = "Data obrigatório")
	private String data;

	@Valid
	private EnderecoDTO enderecoDTOAtendimento;

	@NotBlank(message = "Hora obrigatório")
	private String hora;

	public MarcarAtendimentoDTO() {
	}

	public MarcarAtendimentoDTO(Integer id, Integer cliente, BigDecimal desconto, BigDecimal taxa,
			String formaPagamento, String bandeiraCartao, Integer quantidadeParcelas, String tipoEndereco, String data,
			EnderecoDTO enderecoDTOAtendimento, String hora) {
		this.id = id;
		this.cliente = cliente;
		this.desconto = desconto;
		this.taxa = taxa;
		this.formaPagamento = formaPagamento;
		this.bandeiraCartao = bandeiraCartao;
		this.quantidadeParcelas = quantidadeParcelas;
		this.tipoEndereco = tipoEndereco;
		this.data = data;
		this.hora = hora;
		this.enderecoDTOAtendimento = enderecoDTOAtendimento;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public EnderecoDTO getEnderecoDTOAtendimento() {
		return enderecoDTOAtendimento;
	}

	public void setEnderecoDTOAtendimento(EnderecoDTO enderecoDTOAtendimento) {
		this.enderecoDTOAtendimento = enderecoDTOAtendimento;
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
