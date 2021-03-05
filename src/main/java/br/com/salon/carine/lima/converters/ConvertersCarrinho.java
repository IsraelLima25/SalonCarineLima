package br.com.salon.carine.lima.converters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.salon.carine.lima.dto.ServicoItemCarrinhoDTO;
import br.com.salon.carine.lima.models.ServicoItemCarrinho;

public class ConvertersCarrinho {

	/*Conversor que recebe um mapa de itens do carrinho e converte para uma lista desmembrando chave
	 * e valor para um único objeto 'ServicoItemCarrinhoDTO' para compor uma lista
	 * Israel 25/09/2020 11:14 */
	public static List<ServicoItemCarrinhoDTO> deMapServicoItemCarrinhoParaListServicoItemCarrinhoDTO(
			Map<ServicoItemCarrinho, Integer> mapServicosCarrinho) {

		List<ServicoItemCarrinhoDTO> listaServicoItemCarrinhoDTO = new ArrayList<>();

		mapServicosCarrinho
				.forEach((itemServico, quantidade) -> listaServicoItemCarrinhoDTO.add(new ServicoItemCarrinhoDTO(
						ConvertersServico.deServicoParaServicoDTO(itemServico.getServico()), quantidade, itemServico.getPrecoAtual())));
		
		return listaServicoItemCarrinhoDTO;
	}
}
