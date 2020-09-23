package br.com.salon.carine.lima.models;

import br.com.salon.carine.lima.enuns.BandeiraCartao;

public class Debito extends Pagamento {

	private BandeiraCartao bandeira;

	public Debito(Atendimento atendimento, BandeiraCartao bandeiraCartao) {
		super(atendimento);
		this.bandeira = bandeiraCartao;
	}

	public BandeiraCartao getBandeira() {
		return bandeira;
	}

	public void setBandeira(BandeiraCartao bandeira) {
		this.bandeira = bandeira;
	}

}
