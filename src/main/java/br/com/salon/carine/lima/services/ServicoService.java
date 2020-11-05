package br.com.salon.carine.lima.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.salon.carine.lima.converters.ConvertersServico;
import br.com.salon.carine.lima.dto.ServicoDTO;
import br.com.salon.carine.lima.models.Servico;
import br.com.salon.carine.lima.repositories.ServicoRepository;
import br.com.salon.carine.lima.repositoriessdp.ServicoRepositorySJPA;
import br.com.salon.carine.lima.response.Message;
import br.com.salon.carine.lima.response.ResponseServico;

@Service
public class ServicoService {

	@Autowired
	public ServicoRepository servicoRepository;

	@Autowired
	public ServicoRepositorySJPA servicoRepositorySJPA;
	
	private Integer idLastServico = 0;
	
	private Integer idFirstServico = 0;

	public ResponseServico cadastrar(ServicoDTO servicoDTO) {

		Servico servico = ConvertersServico.deServicoDTOparaServico(servicoDTO);

		servicoRepositorySJPA.save(servico);

		ResponseServico response = new ResponseServico();
		response.setServico(servicoDTO);
		response.setMessage(new Message("Servico", "Serviço cadastrado com sucesso"));

		return response;

	}

	public List<ServicoDTO> listarTodos() {

		List<Servico> servicos = servicoRepositorySJPA.findAll();

		List<ServicoDTO> servicosDTO = new ArrayList<ServicoDTO>();

		for (Servico servico : servicos) {
			ServicoDTO servicoDTO = ConvertersServico.deServicoParaServicoDTO(servico);
			servicosDTO.add(servicoDTO);
		}

		return servicosDTO;
	}

	public Servico buscarServicoPorId(Integer id) {
		Optional<Servico> servicoOptional = servicoRepositorySJPA.findById(id);
		if (servicoOptional.isPresent()) {
			return servicoOptional.get();
		}
		return null;
	}

	public void remover(Integer id) {

		Optional<Servico> optionalServico = this.servicoRepositorySJPA.findById(id);

		if (optionalServico.isPresent()) {
			Servico servico = optionalServico.get();
			servicoRepositorySJPA.delete(servico);
		}
	}

	public Page<Servico> previousPageService(boolean isFirst, Integer number) {

		if (isFirst) {
			int lastPage = (int) servicoRepositorySJPA.count() - 1;
			PageRequest pageRequest = PageRequest.of(lastPage, 1);
			Page<Servico> paginaAterior = servicoRepositorySJPA.findAll(pageRequest);
			return paginaAterior;

		} else {
			PageRequest pageRequest = PageRequest.of(number - 1, 1);
			Page<Servico> paginaAterior = servicoRepositorySJPA.findAll(pageRequest);

			return paginaAterior;
		}
	}

	public Page<Servico> nextPageService(boolean isLast, Integer number) {

		if (isLast) {
			PageRequest pageRequest = PageRequest.of(0, 1);
			Page<Servico> paginaProxima = servicoRepositorySJPA.findAll(pageRequest);

			return paginaProxima;

		} else {
			PageRequest pageRequest = PageRequest.of(number + 1, 1);
			Page<Servico> paginaProxima = servicoRepositorySJPA.findAll(pageRequest);

			return paginaProxima;
		}
	}

	public ResponseServico alterarServico(ServicoDTO servicoDTO) {

		Servico servico = ConvertersServico.deServicoDTOparaServico(servicoDTO);
		servicoRepositorySJPA.save(servico);

		ResponseServico response = new ResponseServico();
		response.setServico(servicoDTO);

		response.setMessage(new Message("Servico", "Serviço Alterado com sucesso"));

		return response;
	}

	public Page<Servico> findPageServico(Integer page, Integer size) {

			PageRequest pageRequest = PageRequest.of(page, size);
			Page<Servico> pagesServico = servicoRepositorySJPA.findAll(pageRequest);

			return pagesServico;
	}

	public Page<Servico> buscarServicoRowNumber(Integer rowNumber) {

		PageRequest pageRequest = PageRequest.of(rowNumber, 1);

		Page<Servico> pageServico = servicoRepositorySJPA.findAll(pageRequest);

		return pageServico;
	}

	public List<Servico> filtrarServicoPorDescricao(String descricao) {

		if (!descricao.equals("")) {
			List<Servico> servicos = servicoRepositorySJPA.searchDescricaoFilter(descricao);

			return servicos;

		} else {

			Page<Servico> findPageServicos = findPageServico(0, 5);
			return findPageServicos.getContent();
		}
	}

	public Servico nextServico(Integer idServicoAtual) {

		if (isLastServico(idServicoAtual)) {
			return firstServico();
		} else {
			Integer idServicoProximo = servicoRepositorySJPA.idServicoProximo(idServicoAtual);
			return servicoRepositorySJPA.findById(idServicoProximo).get();
		}
	}
	
	public boolean isLastServico(Integer idServicoAtual) {
		
		if(idLastServico == idServicoAtual) {
			return true;
		}else {
			return false;
		}
	}
	
	public Servico firstServico() {
		
		Integer idFirstServico = servicoRepositorySJPA.idFirstServico();
		Optional<Servico> optionalServico = servicoRepositorySJPA.findById(idFirstServico);
		if(optionalServico.isPresent()) {
			return optionalServico.get();
		}
		
		return null;
	}
	
	public void atualizarLastId(Integer id) {
		idLastServico = id;
	}

	public void atualizarFirstId(Integer id) {
		idFirstServico = id;
	}
	
	public Integer idLastServico() {
		return servicoRepositorySJPA.idlastServico();
	}
	
	public Integer idFirstServico() {
		return servicoRepositorySJPA.idFirstServico();
	}

	public Servico previousServico(Integer idServicoAtual) {
		if(isFirstServico(idServicoAtual)) {
			return lastServico();
		}else {
			Integer idClienteAnterior = servicoRepositorySJPA.idServicoAnterior(idServicoAtual);
			return servicoRepositorySJPA.findById(idClienteAnterior).get();
		}
	}
	
	public boolean isFirstServico(Integer idServicoAtual) {
		
		if(idFirstServico == idServicoAtual) {
			return true;
		}else {
			return false;
		}
	}
	
	public Servico lastServico() {
		Integer idlastServico = servicoRepositorySJPA.idlastServico();
		Optional<Servico> optionalServico = servicoRepositorySJPA.findById(idlastServico);
		if(optionalServico.isPresent()) {
			return optionalServico.get();
		}
		
		return null;
	}

}
