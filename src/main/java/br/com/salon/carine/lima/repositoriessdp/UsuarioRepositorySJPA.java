package br.com.salon.carine.lima.repositoriessdp;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.salon.carine.lima.models.Usuario;

@Repository
public interface UsuarioRepositorySJPA extends JpaRepository<Usuario, String>{

	Optional<Usuario> findByEmail(String email);
}
