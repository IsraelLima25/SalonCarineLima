package br.com.salon.carine.lima.repositories;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.salon.carine.lima.models.Servico;
import br.com.salon.carine.lima.repositoriessdp.ServicoRepositorySJPA;

@Repository
@Transactional
public class ServicoRepository {

	@PersistenceContext
	public EntityManager manager;

	@Autowired
	public ServicoRepositorySJPA servicoRepositorySJPA;

	public void cadastrar(Servico servico) {

		this.manager.persist(servico);
	}

	public List<Servico> listarTodos() {

		TypedQuery<Servico> typedQuery = this.manager.createQuery("select s from Servico s", Servico.class);
		List<Servico> servicos = typedQuery.getResultList();
		return servicos;

	}

	public Servico buscarServicoPorId(Integer id) {

		Servico findServico = this.manager.find(Servico.class, id);

		return findServico;

	}

	public Servico buscarServicoPorID(Integer id) {

		Servico findServico = this.manager.find(Servico.class, id);

		return findServico;

	}

	public Servico buscarServicoProximoParaAtual(Servico servico) {
		TypedQuery<Servico> typedQuery = this.manager.createQuery("select s1 "
				+ "from Servico s1 where s1.id = (select min(s2.id)" + " from Servico s2 where s2.id > :id)",
				Servico.class);

		typedQuery.setParameter("id", servico.getId());

		Servico servicoProximo = typedQuery.getSingleResult();

		return servicoProximo;
	}

	public Servico buscarServicoAnteriorParaAtual(Servico servico) {

		TypedQuery<Servico> typedQuery = this.manager.createQuery("select s1 "
				+ "from Servico s1 where s1.id = (select MAX(s2.id) " + "from Servico s2 where s2.id < :id)",
				Servico.class);

		typedQuery.setParameter("id", servico.getId());

		Servico servicoAnterior = typedQuery.getSingleResult();

		return servicoAnterior;

	}

	public Servico primeiroServicoCadastrado() {

		TypedQuery<Servico> typedQuery = this.manager.createQuery(
				"select s1 " + "from Servico s1 where s1.id = (select MIN(s2.id) " + "from Servico s2)", Servico.class);

		Servico servico = typedQuery.getSingleResult();

		return servico;

	}

	public Servico ultimoServicoCadastrado() {
		TypedQuery<Servico> typedQuery = this.manager.createQuery(
				"select s1 " + "from Servico s1 where s1.id = (select MAX(s2.id) " + "from Servico s2)", Servico.class);

		Servico servico = typedQuery.getSingleResult();

		return servico;
	}

	public Servico remover(Integer id) {

		Servico servicoRemove = buscarServicoPorId(id);
		this.manager.remove(servicoRemove);
		return servicoRemove;

	}

	public void alterarCliente(Servico servico) {
		this.manager.merge(servico);
	}

	public void numerarLinhas(List<Servico> servicos) {

		for (int i = 0; i < servicos.size(); i++) {
			servicos.get(i).setRowNumber(i);
		}
	}

	public List<Servico> searchDescricaoFilterRowNumber(String nome) {
		long count = servicoRepositorySJPA.count();

		PageRequest pageable = PageRequest.of(0, (int) count);

		Page<Servico> findAllPagingRowNumber = findAllPagingRowNumber(pageable);

		List<Servico> lista = findAllPagingRowNumber.getContent().stream()
				.filter(servico -> servico.getDescricao()
				.toLowerCase().startsWith(nome.toLowerCase()))
				.collect(Collectors.toList());

		return lista;
	}

	public Page<Servico> findAllPagingRowNumber(Pageable pageable) {

		Page<Servico> pageServicos = servicoRepositorySJPA.findAll(pageable);

		numerarLinhas(pageServicos.getContent());

		return pageServicos;
	}

}
