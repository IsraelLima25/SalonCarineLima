package br.com.salon.carine.lima.models;

import javax.persistence.Entity;

import br.com.salon.carine.lima.enuns.BandeiraCartao;

@Entity
public class Debito extends Pagamento {

	private static final long serialVersionUID = 1L;
	
	private BandeiraCartao bandeiraCartao;
	
	public Debito() {
		
	}

	public Debito(BandeiraCartao bandeiraCartao) {
		this.bandeiraCartao = bandeiraCartao;
	}

	public BandeiraCartao getBandeira() {
		return bandeiraCartao;
	}

	public void setBandeira(BandeiraCartao bandeira) {
		this.bandeiraCartao = bandeira;
	}
	
	

}
