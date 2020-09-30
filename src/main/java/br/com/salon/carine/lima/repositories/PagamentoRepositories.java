package br.com.salon.carine.lima.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.salon.carine.lima.models.Pagamento;

@Repository
@Transactional
public class PagamentoRepositories {
	
	@PersistenceContext
	public EntityManager manager;
	
	public void salvarPagamento(Pagamento pagamento) {
		this.manager.persist(pagamento);
	}

}
