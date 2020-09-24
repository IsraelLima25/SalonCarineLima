package br.com.salon.carine.lima.models;

import java.io.Serializable;
import java.math.BigDecimal;

public class ServicoItemCarrinho implements Serializable {

	private static final long serialVersionUID = 1L;

	private Servico servico;
	private Integer quantidade;

	public ServicoItemCarrinho() {
		super();
	}

	public ServicoItemCarrinho(Servico servico) {
		this.servico = servico;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((servico == null) ? 0 : servico.hashCode());
		return result;
	}
	
	public BigDecimal getTotal(Integer quantidade) {
		BigDecimal total = BigDecimal.valueOf(quantidade);
		return servico.getPreco().multiply(total);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ServicoItemCarrinho other = (ServicoItemCarrinho) obj;
		if (servico == null) {
			if (other.servico != null)
				return false;
		} else if (!servico.equals(other.servico))
			return false;
		return true;
	}


}
