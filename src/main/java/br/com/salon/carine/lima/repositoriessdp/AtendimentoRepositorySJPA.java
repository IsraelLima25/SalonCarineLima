package br.com.salon.carine.lima.repositoriessdp;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.salon.carine.lima.models.Atendimento;

@Repository
public interface AtendimentoRepositorySJPA extends JpaRepository<Atendimento, Integer> {
	
	@EntityGraph(value = "Cliente.endereco")
	Page<Atendimento> findByDataBetween(Calendar from, Calendar to, Pageable pageable);
	
	List<Atendimento> findByDataBetween(Calendar from, Calendar de);
	
	List<Atendimento> findByDataBetween(LocalDate from, LocalDate de);
	
	@EntityGraph(value = "Cliente.endereco")
	@Query("SELECT a FROM Atendimento a JOIN a.cliente c WHERE c.nome LIKE :nome%")
	Page<Atendimento> searchNomeFilter(@Param("nome") String nome, Pageable page);
	
	@EntityGraph(value = "Cliente.endereco")
	@Query("SELECT a FROM Atendimento a")
	Page<Atendimento> findAllAtendimentoFetchEnderecoCliente(PageRequest pageRequest);
	
	@EntityGraph(value = "Atendimento.itens")
	Optional<Atendimento> findById(Integer id);
	
	@Query("SELECT MIN(a.id) FROM Atendimento a")
	Integer idFirstAtendimento();
	
	@Query("SELECT MAX(a.id) FROM Atendimento a")
	Integer idLastAtendimento();
	
	@Query("SELECT MIN(a.id) FROM Atendimento a WHERE a.id > :idAtendimentoAtual")
	Integer idAtendimentoProximo (@Param("idAtendimentoAtual") Integer idAtendimentoAtual);
	
	@Query("SELECT MAX(a.id) FROM Atendimento a WHERE a.id < :idAtendimentoAtual")
	Integer idAtendimentoAnterior (@Param("idAtendimentoAtual") Integer idAtendimentoAtual);
	
}
