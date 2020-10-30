package br.com.salon.carine.lima.repositoriessdp;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.salon.carine.lima.models.Lancamento;

@Repository
public interface LancamentoRepositorySJPA extends JpaRepository<Lancamento, Integer> {

	List<Lancamento> findByDataBetween(Calendar from, Calendar to);
	
}
