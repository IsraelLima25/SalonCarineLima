package br.com.salon.carine.lima.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class MarcarAtendimentoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	@Min(value = 1, message = "cliente obrigatório")
	private Integer cliente;

	private BigDecimal desconto;
	private BigDecimal taxa;

	@NotBlank(message = "Pagamento obrigatório")
	private String formaPagamento;

	private String bandeiraCartao;
	private Integer quantidadeParcelas;

	@NotBlank(message = "Endereco obrigatório")
	private String tipoEndereco;

	@NotBlank(message = "Data obrigatório")
	private String data;

	private EnderecoDTO endereco;

	@NotBlank(message = "Hora obrigatório")
	private String hora;

	public MarcarAtendimentoDTO() {
	}

	public MarcarAtendimentoDTO(Integer id, @Min(value = 1, message = "cliente obrigatório") Integer cliente,
			BigDecimal desconto, BigDecimal taxa, @NotBlank(message = "Pagamento obrigatório") String formaPagamento,
			String bandeiraCartao, Integer quantidadeParcelas,
			@NotBlank(message = "Endereco obrigatório") String tipoEndereco,
			@NotBlank(message = "Data obrigatório") String data, EnderecoDTO endereco,
			@NotBlank(message = "Hora obrigatório") String hora) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.desconto = desconto;
		this.taxa = taxa;
		this.formaPagamento = formaPagamento;
		this.bandeiraCartao = bandeiraCartao;
		this.quantidadeParcelas = quantidadeParcelas;
		this.tipoEndereco = tipoEndereco;
		this.data = data;
		this.endereco = endereco;
		this.hora = hora;
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
