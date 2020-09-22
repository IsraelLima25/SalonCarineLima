package br.com.salon.carine.lima.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.salon.carine.lima.converters.ConvertersServico;
import br.com.salon.carine.lima.dto.MessageDTO;
import br.com.salon.carine.lima.dto.ResponseServicoDTO;
import br.com.salon.carine.lima.dto.ServicoDTO;
import br.com.salon.carine.lima.models.Servico;
import br.com.salon.carine.lima.repositories.ServicoRepository;

@Service
public class ServicoService {

	@Autowired
	public ServicoRepository servicoRepository;

	@Autowired
	public NextPreviousServicoService nextPreviousServicoService;

	public ResponseServicoDTO cadastrar(ServicoDTO servicoDTO) {

		Servico servico = ConvertersServico.deServicoDTOparaServico(servicoDTO);

		this.servicoRepository.cadastrar(servico);

		ResponseServicoDTO response = new ResponseServicoDTO();
		response.setServico(servicoDTO);
		response.setMessage(new MessageDTO("Servico", "Serviço cadastrado com sucesso"));

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

	public ServicoDTO buscarServicoAnteriorParaAtual(Servico servicoAtual) {

		Servico servicoAnterior = this.nextPreviousServicoService.anterior(servicoAtual);
		ServicoDTO servicoDTOAnterior = ConvertersServico.deServicoParaServicoDTO(servicoAnterior);

		return servicoDTOAnterior;

	}

	public ServicoDTO remover(Integer id) {

		if (id != null) {
			Servico servico = this.servicoRepository.buscarServicoPorId(id);
			ServicoDTO servicoProximo = buscarServicoProximoParaAtual(servico);
			this.servicoRepository.remover(servico.getId());
			if (servicoProximo != null) {
				return servicoProximo;
			}
		}

		return null;
	}

	public ServicoDTO buscarServicoProximoParaAtual(Servico servicoAtual) {

		ServicoDTO servicoDTOProximo = null;

		Servico servicoProximo = this.nextPreviousServicoService.proximo(servicoAtual);
		if (servicoProximo.getId() != servicoAtual.getId()) {
			servicoDTOProximo = ConvertersServico.deServicoParaServicoDTO(servicoProximo);
		}

		return servicoDTOProximo;
	}

	public ResponseServicoDTO alterarServico(ServicoDTO servicoDTO) {
		
		Servico servico = ConvertersServico.deServicoDTOparaServico(servicoDTO);
		this.servicoRepository.alterarCliente(servico);

		ResponseServicoDTO response = new ResponseServicoDTO();
		response.setServico(servicoDTO);

		response.setMessage(new MessageDTO("Servico", "Serviço Alterado com sucesso"));

		return response;
	}
}
