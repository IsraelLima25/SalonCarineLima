package br.com.salon.carine.lima.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.salon.carine.lima.models.Atendimento;

@Repository
@Transactional
public class AtendimentoRepository {
	
	@PersistenceContext
	public EntityManager manager;
	
	public void marcarAtendimento(Atendimento atendimento) {
		this.manager.persist(atendimento);
	}
	
	public Atendimento buscarAtendimentoPorId(Integer id) {
		
		Atendimento atendimento = manager.find(Atendimento.class, id);
		return atendimento;
	}
	
	
	public void atualizarAtendimento(Atendimento atendimento) {
		this.manager.merge(atendimento);
	}

}
