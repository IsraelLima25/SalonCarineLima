package br.com.salon.carine.lima.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.format.annotation.NumberFormat;

import br.com.salon.carine.lima.enuns.BandeiraCartao;
import br.com.salon.carine.lima.enuns.TipoEndereco;
import br.com.salon.carine.lima.enuns.TipoPagamento;
import br.com.salon.carine.lima.validations.AtendimentoInsertValidator;

@AtendimentoInsertValidator
public class MarcarAtendimentoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private Integer cliente;
	
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal desconto;
	
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal taxa;

	private TipoPagamento tipoPagamento;

	private BandeiraCartao bandeiraCartao;

	private Integer quantidadeParcelas;

	private TipoEndereco tipoEndereco;
	
	private EnderecoDTO enderecoDTOAtendimento;
	
	private String data;


	private String hora;

	public MarcarAtendimentoDTO() {
	}

	public MarcarAtendimentoDTO(Integer id, Integer cliente, BigDecimal desconto, BigDecimal taxa,
			TipoPagamento tipoPagamento, BandeiraCartao bandeiraCartao, Integer quantidadeParcelas, 
			TipoEndereco tipoEndereco, String data, EnderecoDTO enderecoDTOAtendimento, String hora) {
		this.id = id;
		this.cliente = cliente;
		this.desconto = desconto;
		this.taxa = taxa;
		this.tipoPagamento = tipoPagamento;
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

	public TipoPagamento getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(TipoPagamento tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public TipoEndereco getTipoEndereco() {
		return tipoEndereco;
	}

	public void setTipoEndereco(TipoEndereco tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
	}

	public BandeiraCartao getBandeiraCartao() {
		return bandeiraCartao;
	}

	public void setBandeiraCartao(BandeiraCartao bandeiraCartao) {
		this.bandeiraCartao = bandeiraCartao;
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
