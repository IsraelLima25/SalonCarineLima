package br.com.salon.carine.lima.repositoriessdp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.salon.carine.lima.models.Atendimento;

@Repository
public interface AtendimentoRepositorySJPA extends JpaRepository<Atendimento, Integer> {

}
