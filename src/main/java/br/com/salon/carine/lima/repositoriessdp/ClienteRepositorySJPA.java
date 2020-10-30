package br.com.salon.carine.lima.repositoriessdp;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.salon.carine.lima.models.Cliente;

@Repository
public interface ClienteRepositorySJPA extends PagingAndSortingRepository<Cliente, Integer>  {

}
