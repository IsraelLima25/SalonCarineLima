package br.com.salon.carine.lima.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

	public ResponseServico cadastrar(ServicoDTO servicoDTO) {

		Servico servico = ConvertersServico.deServicoDTOparaServico(servicoDTO);

		this.servicoRepository.cadastrar(servico);

		ResponseServico response = new ResponseServico();
		response.setServico(servicoDTO);
		response.setMessage(new Message("Servico", "Serviço cadastrado com sucesso"));

		return response;

	}

	public List<ServicoDTO> listarTodos() {

		List<Servico> servicos = this.servicoRepository.listarTodos();

		List<ServicoDTO> servicosDTO = new ArrayList<ServicoDTO>();

		for (Servico servico : servicos) {
			ServicoDTO servicoDTO = ConvertersServico.deServicoParaServicoDTO(servico);
			servicosDTO.add(servicoDTO);
		}

		return servicosDTO;
	}

	public ServicoDTO buscarServicoPorId(Integer id) {
		Servico servico = this.servicoRepository.buscarServicoPorId(id);
		ServicoDTO servicoDTO = ConvertersServico.deServicoParaServicoDTO(servico);

		return servicoDTO;
	}

	public void remover(Integer id) {

		Optional<Servico> optionalServico = this.servicoRepositorySJPA.findById(id);
		
		if (optionalServico.isPresent()) {
			Servico servico = optionalServico.get();
			servicoRepositorySJPA.delete(servico);
		}
	}
	
	public Page<Servico> previousPageService(boolean isFirst, Integer number) {
		
		if(isFirst) {			
			int lastPage = (int) servicoRepositorySJPA.count() - 1;
			PageRequest pageRequest = PageRequest.of(lastPage, 1);
			Page<Servico> paginaAterior = servicoRepositorySJPA.findAll(pageRequest);
			return paginaAterior;
			
		}else {
			PageRequest pageRequest = PageRequest.of(number - 1, 1);
			Page<Servico> paginaAterior = servicoRepositorySJPA.findAll(pageRequest);
			
			return paginaAterior;
		}
	}
	
	public Page<Servico> nextPageService(boolean isLast, Integer number) {
		
		if(isLast) {
			PageRequest pageRequest = PageRequest.of(0, 1);
			Page<Servico> paginaProxima = servicoRepositorySJPA.findAll(pageRequest);
			
			return paginaProxima;
			
		}else {
			PageRequest pageRequest = PageRequest.of(number + 1, 1);
			Page<Servico> paginaProxima = servicoRepositorySJPA.findAll(pageRequest);
			
			return paginaProxima;
		}
	}

	public ResponseServico alterarServico(ServicoDTO servicoDTO) {
		
		Servico servico = ConvertersServico.deServicoDTOparaServico(servicoDTO);
		this.servicoRepository.alterarCliente(servico);

		ResponseServico response = new ResponseServico();
		response.setServico(servicoDTO);

		response.setMessage(new Message("Servico", "Serviço Alterado com sucesso"));

		return response;
	}
	
	public Page<Servico> findPageServico(Integer page, Integer size) {
		
		if(page > 0) {
		
			PageRequest pageRequest = PageRequest.of(page, size);
			Page<Servico> pagesServico = servicoRepositorySJPA.findAll(pageRequest);
			
			int pointInit = page * 5;
			
			for (Servico servico : pagesServico) {
				servico.setRowNumber(pointInit);
				pointInit++;
			}
			
			return pagesServico;
			
		}else {
			PageRequest pageRequest = PageRequest.of(page, size);
			Page<Servico> pagesServico = servicoRepositorySJPA.findAll(pageRequest);
			
			return pagesServico;
		}
	}
	
	public Integer buscarRowPorID(Integer idServico) {
		
		List<Servico> findAllServicos = (List<Servico>) servicoRepositorySJPA.findAll();
		servicoRepository.numerarLinhas(findAllServicos);
				List<Servico> servicos = findAllServicos.stream()
				.filter(servico -> servico.getId() == idServico)
				.collect(Collectors.toList());
				
		return servicos.get(0).getRowNumber();
	}
	
	public Page<Servico> buscarServicoRowNumber(Integer rowNumber) {
		
		PageRequest pageRequest = PageRequest.of(rowNumber, 1);
		
		Page<Servico> pageServico = servicoRepositorySJPA.findAll(pageRequest);
		
		return pageServico;
	}

	public List<Servico> filtrarServicoPorDescricao(String nome) {
		
		if(!nome.equals("")) {
			List<Servico> servicos = servicoRepository.searchDescricaoFilterRowNumber(nome);
			
			return servicos;
			
		}else {
			
			Page<Servico> findPageServicos = findPageServico(0, 5);
			return findPageServicos.getContent();
		}
	}
}
