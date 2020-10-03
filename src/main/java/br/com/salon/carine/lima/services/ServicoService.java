package br.com.salon.carine.lima.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.salon.carine.lima.converters.ConvertersServico;
import br.com.salon.carine.lima.dto.ServicoDTO;
import br.com.salon.carine.lima.models.Servico;
import br.com.salon.carine.lima.repositories.ServicoRepository;
import br.com.salon.carine.lima.response.Message;
import br.com.salon.carine.lima.response.ResponseServico;

@Service
public class ServicoService {

	@Autowired
	public ServicoRepository servicoRepository;

	@Autowired
	public NextPreviousServicoService nextPreviousServicoService;

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

	public ResponseServico alterarServico(ServicoDTO servicoDTO) {
		
		Servico servico = ConvertersServico.deServicoDTOparaServico(servicoDTO);
		this.servicoRepository.alterarCliente(servico);

		ResponseServico response = new ResponseServico();
		response.setServico(servicoDTO);

		response.setMessage(new Message("Servico", "Serviço Alterado com sucesso"));

		return response;
	}
}
