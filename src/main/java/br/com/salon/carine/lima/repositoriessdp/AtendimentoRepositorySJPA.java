package br.com.salon.carine.lima.repositoriessdp;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.salon.carine.lima.models.Atendimento;

@Repository
public interface AtendimentoRepositorySJPA extends PagingAndSortingRepository<Atendimento, Integer> {
	
	@Query("SELECT a FROM Cliente c INNER JOIN c.atendimentos a where lower(c.nome) like :nome%")
	List<Atendimento> searchNome(@Param("nome") String nome);
	
	Page<Atendimento> findByDataBetween(Calendar from, Calendar to, Pageable pageable);
	
}
