package br.com.salon.carine.lima.converters;

import br.com.salon.carine.lima.dto.ServicoDTO;
import br.com.salon.carine.lima.models.Servico;

public class ConvertersServico {

	public static ServicoDTO deServicoParaServicoDTO(Servico servico) {

		ServicoDTO servicoDTO = new ServicoDTO();
		servicoDTO.setId(servico.getId());
		servicoDTO.setDescricao(servico.getDescricao());
		servicoDTO.setPreco(servico.getPreco());

		return servicoDTO;

	}

	public static Servico deServicoDTOparaServico(ServicoDTO servicoDTO) {

		Servico servico = new Servico();
		servico.setId(servicoDTO.getId());
		servico.setDescricao(servicoDTO.getDescricao());
		servico.setPreco(servicoDTO.getPreco());

		return servico;

	}

}
