package br.com.salon.carine.lima.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class ServicoItemCarrinhoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private ServicoDTO servicoDTO;
	private Integer quantidade;
	private BigDecimal precoTotal;

	public ServicoItemCarrinhoDTO() {

	}

	public ServicoItemCarrinhoDTO(ServicoDTO servicoDTO, Integer quantidade) {
		super();
		this.servicoDTO = servicoDTO;
		this.quantidade = quantidade;
	}

	public ServicoDTO getServicoDTO() {
		return servicoDTO;
	}

	public void setServicoDTO(ServicoDTO servicoDTO) {
		this.servicoDTO = servicoDTO;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPrecoTotal() {
		return precoTotal;
	}

	public void setPrecoTotal(BigDecimal precoTotal) {
		this.precoTotal = precoTotal;
	}
	

}
