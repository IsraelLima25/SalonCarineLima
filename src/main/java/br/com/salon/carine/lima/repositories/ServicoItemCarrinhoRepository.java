package br.com.salon.carine.lima.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.salon.carine.lima.models.ServicoItemCarrinho;

@Repository
@Transactional
public class ServicoItemCarrinhoRepository {
	
	@PersistenceContext
	public EntityManager manager;
	
	public void salvarItem(ServicoItemCarrinho servicoItemCarrinho) {
		this.manager.persist(servicoItemCarrinho);
	}

}
