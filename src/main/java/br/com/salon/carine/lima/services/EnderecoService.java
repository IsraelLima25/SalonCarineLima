package br.com.salon.carine.lima.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.salon.carine.lima.models.Endereco;
import br.com.salon.carine.lima.repositories.EnderecoRepository;

@Service
public class EnderecoService {
	
	@Autowired
	private EnderecoRepository enderecoRepository;

	public Endereco homeAdress(Integer idEndereco) {
		Endereco endereco = this.enderecoRepository.buscarEnderecoPorId(idEndereco);
		return endereco;
	}
	
	public Endereco salvarEndereco(Endereco endereco) {
		Endereco enderecoSalvo = enderecoRepository.salvarEndereco(endereco);
		return enderecoSalvo;
	}
}
