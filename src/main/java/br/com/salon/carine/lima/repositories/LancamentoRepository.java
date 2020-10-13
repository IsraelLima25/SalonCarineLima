package br.com.salon.carine.lima.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.salon.carine.lima.models.Lancamento;

@Repository
@Transactional
public class LancamentoRepository {

	@PersistenceContext
	public EntityManager manager;

	public void lancar(Lancamento lancamento) {
		manager.persist(lancamento);
	}
}
