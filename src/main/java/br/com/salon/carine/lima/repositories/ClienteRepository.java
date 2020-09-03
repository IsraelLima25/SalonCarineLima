package br.com.salon.carine.lima.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class ClienteRepository {

	@PersistenceContext
	public EntityManager manager;

}
