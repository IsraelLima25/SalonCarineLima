package br.com.salon.carine.lima.repositoriessdp;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.salon.carine.lima.models.Cliente;

@Repository
public interface ClienteRepositorySJPA extends JpaRepository<Cliente, Integer>  {
	
	@EntityGraph(value = "Cliente.endereco")
	Page<Cliente> findAll(Pageable pageable);
	
	@EntityGraph(value = "Cliente.endereco")
	Optional<Cliente> findById(Integer id);
	
	/* recupera id próximo cliente a partir do cliente atual */
	@Query("SELECT MIN(c.id) FROM Cliente c WHERE c.id > :idClienteAtual")
	Integer idClienteProximo (@Param("idClienteAtual") Integer idClienteAtual);
	
	/* recupera id do último cliente */
	@Query("SELECT MAX(c.id) FROM Cliente c")
	Integer IdlastCliente ();
	
	/* recupera id do primeiro cliente */
	@Query("SELECT MIN(c.id) FROM Cliente c")
	Integer firstCliente();
	
	
	
}
