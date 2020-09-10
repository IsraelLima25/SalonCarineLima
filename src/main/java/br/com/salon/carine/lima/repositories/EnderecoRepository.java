package br.com.salon.carine.lima.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class EnderecoRepository {

	@PersistenceContext
	public EntityManager manager;
	
//	public void cadastrarEndereco(Endereco endereco) {
//		this.manager.persist(endereco);
//	}
	


}
