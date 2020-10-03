package br.com.salon.carine.lima.repositories;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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

	public List<Atendimento> listarTodos() {
		TypedQuery<Atendimento> typedQuery = this.manager.createQuery
				("select a from Atendimento a", Atendimento.class);
		List<Atendimento> atendimentos = typedQuery.getResultList();
		return atendimentos;
	}

	public List<Atendimento> getAtendimentosFilterData
				(Calendar dataInicio, Calendar dataFim) {
		
		if(dataInicio != null && dataFim != null) {
			TypedQuery<Atendimento> typedQuery = this.manager.createQuery
			("select a from Atendimento a where data BETWEEN :dataInicio AND :dataFim", Atendimento.class);
			typedQuery.setParameter("dataInicio", dataInicio);
			typedQuery.setParameter("dataFim", dataFim);
			List<Atendimento> atendimentos = typedQuery.getResultList();
			return atendimentos;
		} else if (dataInicio != null && dataFim == null) {
			TypedQuery<Atendimento> typedQuery = this.manager.createQuery
			("select a from Atendimento a where data >= :dataInicio", Atendimento.class);
			typedQuery.setParameter("dataInicio", dataInicio);
			List<Atendimento> atendimentos = typedQuery.getResultList();
			return atendimentos;
		}else {
			return listarTodos();
		}
		
		
	}

}
