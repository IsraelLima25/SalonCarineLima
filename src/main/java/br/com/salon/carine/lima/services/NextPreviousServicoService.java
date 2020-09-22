package br.com.salon.carine.lima.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.salon.carine.lima.models.Servico;
import br.com.salon.carine.lima.repositories.ServicoRepository;

@Service
public class NextPreviousServicoService implements FilaRegistro<Servico> {
	
	@Autowired
	public ServicoRepository servicoRepository;

	@Override
	public Servico proximo(Servico servicoAtual) {
		
		if (isUnicoRegistroLista()) {

			return servicoAtual;

		} else if (isAtualUltimoLista(servicoAtual)) {
			Servico servicoProximo = buscarPrimeiroLista();
			return servicoProximo;

		} else {

			Servico servicoProximo = this.servicoRepository.
					buscarServicoProximoParaAtual(servicoAtual);
			return servicoProximo;
		}

	}

	@Override
	public Servico anterior(Servico servicoAtual) {
		
		if (isUnicoRegistroLista()) {
			return null;
		} else if (isAtualPrimeiroLista(servicoAtual)) {
			Servico servicoAnterior = buscarUltimoLista();
			return servicoAnterior;
		} else {
			Servico servicoAnterior = this.servicoRepository.buscarServicoAnteriorParaAtual(servicoAtual);
			return servicoAnterior;
		}
	}

	@Override
	public boolean isUnicoRegistroLista() {

		Servico ultimoServicoCadastrado = buscarUltimoLista();
		Servico primeiroServicoCadastrado = buscarPrimeiroLista();

		if (ultimoServicoCadastrado.getId() == primeiroServicoCadastrado.getId()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isAtualUltimoLista(Servico servico) {
		Servico ultimoServicoCadastrado = buscarUltimoLista();

		if (servico.getId().equals(ultimoServicoCadastrado.getId())) {
			return true;
		} else {
			return false;
		}	}

	@Override
	public boolean isAtualPrimeiroLista(Servico servico) {
		

		Servico primeiroServicoCadastrado = buscarPrimeiroLista();

		if (servico.getId() == primeiroServicoCadastrado.getId()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Servico buscarPrimeiroLista() {
		return this.servicoRepository.primeiroServicoCadastrado();
	}

	@Override
	public Servico buscarUltimoLista() {
		return this.servicoRepository.ultimoServicoCadastrado();
	}

}
