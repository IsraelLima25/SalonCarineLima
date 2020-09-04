package br.com.salon.carine.lima.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.salon.carine.lima.models.Cliente;

@Repository
@Transactional
public class ClienteRepository {

	@Autowired
	private EnderecoRepository enderecoRepository;

	@PersistenceContext
	public EntityManager manager;

	public void cadastrar(Cliente cliente) {

		this.enderecoRepository.cadastrar(cliente.getEndereco());
		this.manager.persist(cliente);

	}

}
