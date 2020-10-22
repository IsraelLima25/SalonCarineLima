package br.com.salon.carine.lima.repositoriessdp;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.salon.carine.lima.models.Lancamento;

@Repository
public interface LancamentoRepository extends PagingAndSortingRepository<Lancamento, Integer> {

}
