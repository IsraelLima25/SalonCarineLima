package br.com.salon.carine.lima.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.salon.carine.lima.models.Cliente;

@Repository
@Transactional
public class ClienteRepository {

	@PersistenceContext
	public EntityManager manager;

	public void cadastrar(Cliente cliente) {

		this.manager.persist(cliente);
	}

	public List<Cliente> listarTodos() {

		TypedQuery<Cliente> typedQuery = this.manager.createQuery("select c from Cliente c", Cliente.class);
		List<Cliente> clientes = typedQuery.getResultList();
		return clientes;
	}

	public Cliente remover(Integer id) {

		Cliente clienteRemove = buscarClientePorID(id);
		this.manager.remove(clienteRemove);
		return clienteRemove;

	}

	public void alterarCliente(Cliente cliente) {
		this.manager.merge(cliente);
	}

	public Cliente buscarClientePorID(Integer id) {

		Cliente findCliente = this.manager.find(Cliente.class, id);

		return findCliente;
	}
	
	public Cliente buscarClienteAnteriorParaAtual(Cliente cliente) {
		
		Cliente clienteAnterior;
		
		Cliente ultimoClienteCadastrado = ultimoClienteCadastrado();
		Cliente primeiroClienteCadastrado = primeiroClienteCadastrado();
		
		if(cliente.getId() == primeiroClienteCadastrado.getId()) {
			clienteAnterior = ultimoClienteCadastrado;
			return clienteAnterior;
			
		}else {
			
			TypedQuery<Cliente> typedQuery = this.manager.createQuery(
					"select c1 "
							+ "from Cliente c1 where c1.id = (select MAX(c2.id) "
							+ "from Cliente c2 where c2.id < :id)",Cliente.class);
			typedQuery.setParameter("id", cliente.getId());
			
			clienteAnterior = typedQuery.getSingleResult();
			
			return clienteAnterior;
		}
		
	}
	
	public Cliente buscarClienteProximoParaAtual(Cliente cliente) {
		
		Cliente clienteProximo = null;
		
		Cliente ultimoClienteCadastrado = ultimoClienteCadastrado();
		Cliente primeiroClienteCadastrado = primeiroClienteCadastrado();
		
		if(ultimoClienteCadastrado.getId() == primeiroClienteCadastrado.getId()) {			
			return clienteProximo;
			
		}else if(cliente.getId() == ultimoClienteCadastrado.getId()) { 
			clienteProximo = primeiroClienteCadastrado;
			return clienteProximo;
		}
		else {
			TypedQuery<Cliente> typedQuery = this.manager.createQuery("select c1 "
					+ "from Cliente c1 where c1.id = (select min(c2.id)"
					+ " from Cliente c2 where c2.id > :id)", Cliente.class);
			
			typedQuery.setParameter("id", cliente.getId());
			
			clienteProximo = typedQuery.getSingleResult();
			
			return clienteProximo;
		}
		
		
	}
	
	public Cliente ultimoClienteCadastrado() {
		
		TypedQuery<Cliente> typedQuery = this.manager.createQuery("select c1 "
				+ "from Cliente c1 where c1.id = (select MAX(c2.id) "
				+ "from Cliente c2)",Cliente.class);
		
		Cliente cliente = typedQuery.getSingleResult();
		
		return cliente;
		
	}
	
	public Cliente primeiroClienteCadastrado() {
		
		TypedQuery<Cliente> typedQuery = this.manager.createQuery("select c1 "
				+ "from Cliente c1 where c1.id = (select MIN(c2.id) "
				+ "from Cliente c2)",Cliente.class);
		
		Cliente cliente = typedQuery.getSingleResult();
		
		return cliente;
	}
}
