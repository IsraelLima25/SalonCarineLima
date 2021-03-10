package br.com.salon.carine.lima.models;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ServicoItemCarrinho implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "atendimento_id")
	private Atendimento atendimento;
	
	@ManyToOne
	@JoinColumn(name = "servico_id")
	private Servico servico;

	private Integer quantidade;
	
	private BigDecimal precoAtual;

	public ServicoItemCarrinho() {
	}

	public ServicoItemCarrinho(Servico servico) {
		this.servico = servico;
	}

	public Atendimento getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public BigDecimal getTotal(Integer quantidadeItem) {
		BigDecimal total = this.servico.getPreco().multiply(BigDecimal.valueOf(quantidadeItem));
		return total;
	}
	
	public BigDecimal getTotalPorPrecoAtual(Integer quantidadeItem) {
		BigDecimal total = this.getPrecoAtual().multiply(BigDecimal.valueOf(quantidadeItem));
		return total;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	public BigDecimal getPrecoAtual() {
		return precoAtual;
	}
	
	public void setPrecoAtual(BigDecimal precoAtual) {
		this.precoAtual = precoAtual;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((servico == null) ? 0 : servico.hashCode());
		return result;
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
