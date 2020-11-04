package br.com.salon.carine.lima.repositoriessdp;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.salon.carine.lima.models.Servico;

@Repository
public interface ServicoRepositorySJPA extends JpaRepository<Servico, Integer> {
	
	@Query("FROM Servico s WHERE s.descricao LIKE :descricao%")
	List<Servico> searchDescricaoFilter(@Param("descricao") String descricao);
	
	@Query("SELECT MIN(s.id) FROM Servico s")
	Integer idFirstServico();
	
	@Query("SELECT MAX(s.id) FROM Servico s")
	Integer idlastServico();
	
	@Query("SELECT MAX(s.id) FROM Servico s WHERE s.id < :idServicoAtual")
	Integer idServicoAnterior (@Param("idServicoAtual") Integer idServicoAtual);
	
	@Query("SELECT MIN(s.id) FROM Servico s WHERE s.id > :idServicoAtual")
	Integer idServicoProximo (@Param("idServicoAtual") Integer idServicoAtual);
	
}
