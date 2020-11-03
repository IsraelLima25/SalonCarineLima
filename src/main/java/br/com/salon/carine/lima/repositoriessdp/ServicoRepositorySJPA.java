package br.com.salon.carine.lima.repositoriessdp;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.salon.carine.lima.models.Servico;

@Repository
public interface ServicoRepositorySJPA extends PagingAndSortingRepository<Servico, Integer> {
	
}
