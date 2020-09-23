package br.com.salon.carine.lima.models;

import br.com.salon.carine.lima.enuns.BandeiraCartao;

public class Credito extends Pagamento {

	private BandeiraCartao bandeiraCartao;

	public Credito(Atendimento atendimento, BandeiraCartao bandeiraCartao) {
		super(atendimento);
		this.bandeiraCartao = bandeiraCartao;
		this.bandeiraCartao = bandeiraCartao;
	}

	public BandeiraCartao getBandeiraCartao() {
		return bandeiraCartao;
	}

	public void setBandeiraCartao(BandeiraCartao bandeiraCartao) {
		this.bandeiraCartao = bandeiraCartao;
	}

}
