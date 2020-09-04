package br.com.salon.carine.lima.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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

	public List<Cliente> listarTodos() {

		TypedQuery<Cliente> typedQuery = this.manager.createQuery("select c from Cliente c", Cliente.class);
		List<Cliente> clientes = typedQuery.getResultList();

		return clientes;
	}

	public Cliente remover(Integer id) {

		TypedQuery<Cliente> typedQuery = this.manager.createQuery("select c from Cliente c where c.id = :id",
				Cliente.class);
		typedQuery.setParameter("id", id);

		Cliente clienteRemove = typedQuery.getSingleResult();

		this.manager.remove(clienteRemove);

		return clienteRemove;
	}

}
