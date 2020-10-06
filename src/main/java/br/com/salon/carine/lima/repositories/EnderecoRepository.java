package br.com.salon.carine.lima.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.salon.carine.lima.models.Cliente;
import br.com.salon.carine.lima.models.Endereco;

@Repository
@Transactional
public class EnderecoRepository {

	@PersistenceContext
	public EntityManager manager;
	
	public Endereco buscarEnderecoPorCliente(Cliente cliente) {
		if(cliente != null) {
			Endereco endereco = this.manager.find(Endereco.class, cliente.getId());
			return endereco;
		}
		
		return null;
	}
	
	public Endereco salvarEndereco(Endereco endereco) {
		
		this.manager.persist(endereco);
		TypedQuery<Endereco> typedQuery = this.manager.createQuery
		("select end1 from Endereco end1 where end1.id = (select Max(end2.id) from Endereco end2)",
				Endereco.class);
		
		Endereco enderecoCadastrado = typedQuery.getSingleResult();
		
		return enderecoCadastrado;
	}
}
