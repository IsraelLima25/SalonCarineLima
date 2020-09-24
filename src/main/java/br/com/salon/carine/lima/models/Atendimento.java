package br.com.salon.carine.lima.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import br.com.salon.carine.lima.enuns.StatusAtendimento;

public class Atendimento implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private List<ServicoItemCarrinho> servicos;
	private Cliente cliente;
	private BigDecimal valorTotal;
	private LocalDateTime dataHora;
	private Endereco endereco;
	private StatusAtendimento status;
	private Pagamento pagamento;
	private BigDecimal desconto;

	public Atendimento() {
	}

	public Atendimento(Integer id, List<ServicoItemCarrinho> servicos, Cliente cliente, BigDecimal valorTotal,
			LocalDateTime dataHora, Endereco endereco, StatusAtendimento status, Pagamento pagamento,
			BigDecimal desconto) {
		this.id = id;
		this.servicos = servicos;
		this.cliente = cliente;
		this.valorTotal = valorTotal;
		this.dataHora = dataHora;
		this.endereco = endereco;
		this.status = status;
		this.pagamento = pagamento;
		this.desconto = desconto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<ServicoItemCarrinho> getServicos() {
		return servicos;
	}

	public void setServicos(List<ServicoItemCarrinho> servicos) {
		this.servicos = servicos;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public StatusAtendimento getStatus() {
		return status;
	}

	public void setStatus(StatusAtendimento status) {
		this.status = status;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public BigDecimal getDesconto() {
		return desconto;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Atendimento other = (Atendimento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
