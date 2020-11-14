package br.com.salon.carine.lima.repositoriessdp;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.salon.carine.lima.models.Recovery;

@Repository
public interface RecoveryRepositorySJPA extends JpaRepository<Recovery, Integer> {
	
	Optional<String> findByChave(String key);
	
}
