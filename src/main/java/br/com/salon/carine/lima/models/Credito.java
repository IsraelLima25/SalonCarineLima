package br.com.salon.carine.lima.models;

import javax.persistence.Entity;

import br.com.salon.carine.lima.enuns.BandeiraCartao;

@Entity
public class Credito extends Pagamento {

	private static final long serialVersionUID = 1L;

	private BandeiraCartao bandeiraCartao;
	private Integer quantidadeParcelas;

	public Credito() {
	}

	public Credito(BandeiraCartao bandeiraCartao, Integer quantidadeParcelas) {
		this.bandeiraCartao = bandeiraCartao;
		this.setQuantidadeParcelas(quantidadeParcelas);
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

}
