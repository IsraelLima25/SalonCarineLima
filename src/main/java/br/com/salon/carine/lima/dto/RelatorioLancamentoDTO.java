package br.com.salon.carine.lima.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import br.com.salon.carine.lima.models.Lancamento;

public class RelatorioLancamentoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Lancamento> lancamentos;

	private BigDecimal totalPeriodo;

	public RelatorioLancamentoDTO() {

	}

	public RelatorioLancamentoDTO(List<Lancamento> lancamentos, BigDecimal valorTotal) {
		super();
		this.lancamentos = lancamentos;
		this.totalPeriodo = valorTotal;
	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}

	public BigDecimal getTotalPeriodo() {
		return totalPeriodo;
	}

	public void setTotalPeriodo(BigDecimal totalPeriodo) {
		this.totalPeriodo = totalPeriodo;
	}

}
