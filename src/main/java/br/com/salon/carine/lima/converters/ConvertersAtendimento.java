package br.com.salon.carine.lima.converters;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.salon.carine.lima.dto.AtendimentoDTO;
import br.com.salon.carine.lima.dto.AtendimentoListaDTO;
import br.com.salon.carine.lima.dto.ClienteDTO;
import br.com.salon.carine.lima.dto.EnderecoDTO;
import br.com.salon.carine.lima.dto.ServicoDTO;
import br.com.salon.carine.lima.dto.ServicoItemCarrinhoDTO;
import br.com.salon.carine.lima.models.Atendimento;
import br.com.salon.carine.lima.models.ServicoItemCarrinho;

public class ConvertersAtendimento {

	public static List<AtendimentoListaDTO> deListaAtendimentoParaListaAtendimentoDTO(
			List<Atendimento> listaAtendimentos) {

		List<AtendimentoListaDTO> atendimentosDTO = new ArrayList<>();

		for (Atendimento atendimento : listaAtendimentos) {
			AtendimentoListaDTO atendimentoLista = new AtendimentoListaDTO();
			atendimentoLista.setId(atendimento.getId());
			atendimentoLista.setCliente(atendimento.getCliente());
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			atendimentoLista.setData(dateFormat.format(atendimento.getData().getTime()));
			SimpleDateFormat horaFormat = new SimpleDateFormat("HH:mm");
			atendimentoLista.setHora(horaFormat.format(atendimento.getHora().getTime()));
			atendimentosDTO.add(atendimentoLista);
		}

		return atendimentosDTO;

	}

	public static AtendimentoDTO deAtendimentoParaAtendimentoDTO(Atendimento atendimento) {

		AtendimentoDTO atendimentoDTO = new AtendimentoDTO();
		ClienteDTO clienteDTO = ConvertersCliente.deClienteParaClienteDTO(atendimento.getCliente());
		EnderecoDTO enderecoDTO = ConvertersEndereco.deEnderecoParaEnderecoDTO(atendimento.getEndereco());

		SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat horaFormat = new SimpleDateFormat("HH:mm");

		atendimentoDTO.setCliente(clienteDTO);
		atendimentoDTO.setData(dataFormat.format(atendimento.getData().getTime()));
		atendimentoDTO.setDesconto(atendimento.getDesconto());
		atendimentoDTO.setEndereco(enderecoDTO);
		atendimentoDTO.setTipoEndereco(atendimento.getTipoEndereco());
		atendimentoDTO.setHora(horaFormat.format(atendimento.getHora()));
		atendimentoDTO.setId(atendimento.getId());
		atendimentoDTO.setPagamento(atendimento.getPagamento());
		atendimentoDTO.setStatus(atendimento.getStatus());
		atendimentoDTO.setTaxa(atendimento.getTaxa());
		atendimentoDTO.setTotalBruto(atendimento.getTotalBruto());
		atendimentoDTO.setTotalLiquido(atendimento.getTotalLiquido());

		for (ServicoItemCarrinho item : atendimento.getItens()) {
			ServicoItemCarrinhoDTO itemDTO = new ServicoItemCarrinhoDTO();
			itemDTO.setQuantidade(item.getQuantidade());
			itemDTO.setPrecoTotal(item.getTotal(item.getQuantidade()));
			ServicoDTO servicoDTO = ConvertersServico.deServicoParaServicoDTO(item.getServico());
			itemDTO.setServicoDTO(servicoDTO);
			atendimentoDTO.getItens().add(itemDTO);
		}

		return atendimentoDTO;
	}

}
