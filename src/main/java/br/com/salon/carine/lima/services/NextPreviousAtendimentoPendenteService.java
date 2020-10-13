package br.com.salon.carine.lima.services;

import br.com.salon.carine.lima.models.Atendimento;

public class NextPreviousAtendimentoPendenteService implements FilaRegistro<Atendimento> {

	@Override
	public Atendimento proximo(Atendimento tipo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Atendimento anterior(Atendimento tipo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isUnicoRegistroLista() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAtualUltimoLista(Atendimento tipo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAtualPrimeiroLista(Atendimento tipo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Atendimento buscarPrimeiroLista() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Atendimento buscarUltimoLista() {
		// TODO Auto-generated method stub
		return null;
	}

}
