package br.com.salon.carine.lima.repositories;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import br.com.salon.carine.lima.models.Atendimento;
import br.com.salon.carine.lima.repositoriessdp.AtendimentoRepositorySJPA;

@Repository
@Transactional
public class AtendimentoRepository {
	
	@PersistenceContext
	public EntityManager manager;
	
	@Autowired
	public AtendimentoRepositorySJPA atendimentoRepositorySJPA;
	
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
	
	public Page<Atendimento> findAllPagingRowNumber(Pageable pageable){
		
		Page<Atendimento> pageAtendimentos = atendimentoRepositorySJPA.findAll(pageable);
		
		numerarLinhas(pageAtendimentos.getContent());
		
		return pageAtendimentos;
	}
	
	public List<Atendimento> searchNomeFilterRowNumber(String nome){
		
		long count = atendimentoRepositorySJPA.count();
		
		PageRequest pageable = PageRequest.of(0, (int) count);
		
		Page<Atendimento> findAllPagingRowNumber = findAllPagingRowNumber(pageable);
		
		List<Atendimento> lista = findAllPagingRowNumber.getContent()
			.stream()
				.filter(atendimento -> atendimento.getCliente().getNome()
					.toLowerCase().startsWith(nome.toLowerCase()))
						.collect(Collectors.toList());
		
		return lista;
	
	}
	
	public Page<Atendimento> findByDataBetweenRowNumber(Calendar dataInicio, 
			Calendar dataFim) {
		
		long count = atendimentoRepositorySJPA.count();
		
		PageRequest pageable = PageRequest.of(0, (int) count);
		
		Page<Atendimento> findAllPagingRowNumber = findAllPagingRowNumber(pageable);
		
		Page<Atendimento> findByDataBetween = atendimentoRepositorySJPA
				.findByDataBetween(dataInicio, dataFim, pageable);
		
		List<Atendimento> lista = findAllPagingRowNumber.getContent()
			.stream()
				.filter(atendimento -> findByDataBetween
						.getContent()
							.contains(atendimento))
								.collect(Collectors.toList());
		
		Page<Atendimento> page = new PageImpl<Atendimento>(lista);
		
		return page;
	}
	
	public void numerarLinhas(List<Atendimento> atendimentos) {
		
		for (int i = 0; i < atendimentos.size(); i++) {
			atendimentos.get(i).setRowNumber(i);
		}
	}

}

