package br.com.salon.carine.lima.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.salon.carine.lima.models.Cliente;
import br.com.salon.carine.lima.repositories.ClienteRepository;

@Service
public class NextPreviousClienteService implements FilaRegistro<Cliente> {

	@Autowired
	public ClienteRepository clienteRepository;

	@Override
	public boolean isUnicoRegistroLista() {

		Cliente ultimoClienteCadastrado = buscarUltimoLista();
		Cliente primeiroClienteCadastrado = buscarPrimeiroLista();

		if (ultimoClienteCadastrado.getId() == primeiroClienteCadastrado.getId()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isAtualUltimoLista(Cliente cliente) {

		Cliente ultimoClienteCadastrado = buscarUltimoLista();

		if (cliente.getId().equals(ultimoClienteCadastrado.getId())) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isAtualPrimeiroLista(Cliente cliente) {

		Cliente primeiroClienteCadastrado = buscarPrimeiroLista();

		if (cliente.getId() == primeiroClienteCadastrado.getId()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Cliente buscarPrimeiroLista() {
		return this.clienteRepository.primeiroClienteCadastrado();
	}

	@Override
	public Cliente buscarUltimoLista() {
		return this.clienteRepository.ultimoClienteCadastrado();
	}

	@Override
	public Cliente proximo(Cliente clienteAtual) {

		if (isUnicoRegistroLista()) {

			return null;

		} else if (isAtualUltimoLista(clienteAtual)) {
			Cliente clienteProximo = buscarPrimeiroLista();
			return clienteProximo;

		} else {

			Cliente clienteProximo = this.clienteRepository.buscarClienteProximoParaAtual(clienteAtual);
			return clienteProximo;
		}
	}

	@Override
	public Cliente anterior(Cliente clienteAtual) {		

		if (isUnicoRegistroLista()) {
			return null;
		} else if (isAtualPrimeiroLista(clienteAtual)) {
			Cliente clienteAnterior = buscarUltimoLista();
			return clienteAnterior;
		} else {
			Cliente clienteAnterior = this.clienteRepository.buscarClienteAnteriorParaAtual(clienteAtual);
			return clienteAnterior;
		}

	}

}
