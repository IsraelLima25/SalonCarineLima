package br.com.salon.carine.lima.repositoriessdp;

import java.util.Calendar;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.salon.carine.lima.models.Atendimento;

@Repository
public interface AtendimentoRepositorySJPA extends PagingAndSortingRepository<Atendimento, Integer> {
	
	Page<Atendimento> findByDataBetween(Calendar from, Calendar to, Pageable pageable);
	
}
