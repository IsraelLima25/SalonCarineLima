package br.com.salon.carine.lima.repositories;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.salon.carine.lima.models.Cliente;
import br.com.salon.carine.lima.repositoriessdp.ClienteRepositorySJPA;

@Repository
@Transactional
public class ClienteRepository {

	@PersistenceContext
	public EntityManager manager;
	
	@Autowired
	public ClienteRepositorySJPA clienteRepositorySJPA;

	public void cadastrar(Cliente cliente) {

		manager.persist(cliente);
	}
	
	public void alterar(Cliente cliente) {
		manager.merge(cliente);
	}

	public List<Cliente> listarTodos() {

		TypedQuery<Cliente> typedQuery = this.manager.createQuery("select c from Cliente c JOIN FETCH c.endereco", Cliente.class);
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

	public Cliente buscarClientePorID(Integer idCliente) {

		TypedQuery<Cliente> typedQuery = this.manager.createQuery
				("select c from Cliente c JOIN FETCH c.endereco where c.id =:idCliente",
						Cliente.class);
		typedQuery.setParameter("idCliente", idCliente);
		
		Cliente cliente = typedQuery.getSingleResult();

		return cliente;
	}
	
	public Cliente buscarClienteAnteriorParaAtual(Cliente cliente) {
			
			TypedQuery<Cliente> typedQuery = this.manager.createQuery(
					"select c1 "
							+ "from Cliente c1 where c1.id = (select MAX(c2.id) "
							+ "from Cliente c2 where c2.id < :id)",Cliente.class);
			
			typedQuery.setParameter("id", cliente.getId());
			
			Cliente clienteAnterior = typedQuery.getSingleResult();
			
			return clienteAnterior;
	}
	
	public Cliente buscarClienteProximoParaAtual(Cliente cliente) {
		
		TypedQuery<Cliente> typedQuery = this.manager.createQuery("select c1 "
				+ "from Cliente c1 where c1.id = (select min(c2.id)"
				+ " from Cliente c2 where c2.id > :id)", Cliente.class);
		
		typedQuery.setParameter("id", cliente.getId());
		
		Cliente clienteProximo = typedQuery.getSingleResult();
		
		return clienteProximo;

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
	
	public void numerarLinhas(List<Cliente> clientes) {
		
//		for (int i = 0; i < clientes.size(); i++) {
//			clientes.get(i).setRowNumber(i);
//		}
	}
	
	public List<Cliente> searchNomeFilterRowNumber(String nome){
		
		long count = clienteRepositorySJPA.count();
		
		PageRequest pageable = PageRequest.of(0, (int) count);
		
		Page<Cliente> findAllPagingRowNumber = findAllPagingRowNumber(pageable);
		
		List<Cliente> lista = findAllPagingRowNumber.getContent()
			.stream()
				.filter(cliente -> cliente.getNome()
					.toLowerCase().startsWith(nome.toLowerCase()))
						.collect(Collectors.toList());
		
		return lista;
	}
	
	public Page<Cliente> findAllPagingRowNumber(Pageable pageable){
		
		Page<Cliente> pageAtendimentos = clienteRepositorySJPA.findAll(pageable);
		
		numerarLinhas(pageAtendimentos.getContent());
		
		return pageAtendimentos;
	}
}
