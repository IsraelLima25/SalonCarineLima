package br.com.salon.carine.lima.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.salon.carine.lima.converters.ConvertersCarrinho;
import br.com.salon.carine.lima.converters.ConvertersServico;
import br.com.salon.carine.lima.dto.ServicoDTO;
import br.com.salon.carine.lima.dto.ServicoItemCarrinhoDTO;
import br.com.salon.carine.lima.models.Carrinho;
import br.com.salon.carine.lima.models.Servico;
import br.com.salon.carine.lima.models.ServicoItemCarrinho;

@Service
public class CarrinhoService {

	@Autowired
	private Carrinho carrinho;
	
	public List<ServicoItemCarrinhoDTO> getServicosCarrinho(){
		
		Map<ServicoItemCarrinho, Integer> mapServicosCarrinho = carrinho.getServicos();
		List<ServicoItemCarrinhoDTO> listServicosCarrinho = ConvertersCarrinho.
		deMapServicoItemCarrinhoParaListServicoItemCarrinhoDTO(mapServicosCarrinho);
		
		calcularPrecoTotalPorItem(listServicosCarrinho);
		
		return listServicosCarrinho;
	}

	private void calcularPrecoTotalPorItem(List<ServicoItemCarrinhoDTO> listServicosCarrinho) {
		
		listServicosCarrinho.forEach((servicoItemCarrinhoDTO) -> 
			servicoItemCarrinhoDTO.setPrecoTotal(carrinho.getTotalUnidadeServicoCarrinho(new ServicoItemCarrinho(
					ConvertersServico.deServicoDTOparaServico(
							servicoItemCarrinhoDTO.getServicoDTO()))))
		);
	}

	public ServicoItemCarrinhoDTO addServicoCarrinho(ServicoDTO servicoDTO) {

		Servico servico = ConvertersServico.deServicoDTOparaServico(servicoDTO);
		ServicoItemCarrinho servicoAdicionadoCarrinho = new ServicoItemCarrinho();
		servicoAdicionadoCarrinho.setServico(servico);
		servicoAdicionadoCarrinho.setPrecoAtual(servico.getPreco());

		ServicoItemCarrinhoDTO itemAdicionado = carrinho.adicionarItemCarrinho(servicoAdicionadoCarrinho);				

		return itemAdicionado;
	}

	public ServicoItemCarrinhoDTO removerItemCarrinho(ServicoDTO servicoDTO){
		
		Servico servico = ConvertersServico.deServicoDTOparaServico(servicoDTO);
		ServicoItemCarrinho servicoRemovidoCarrinho = new ServicoItemCarrinho();
		servicoRemovidoCarrinho.setServico(servico);
		
		ServicoItemCarrinhoDTO itemRemovido = carrinho.removerUnidadeItemCarrinho(servicoRemovidoCarrinho);
		
		return itemRemovido;
	}
	
	public boolean removerServicoCarrinho(ServicoDTO servicoDTO) {
		
		Servico servico = ConvertersServico.deServicoDTOparaServico(servicoDTO);
		ServicoItemCarrinho servicoItemCarrinho = new ServicoItemCarrinho();
		servicoItemCarrinho.setServico(servico);
		
		boolean removido = carrinho.removerItemCarrinho(servicoItemCarrinho);
		
		return removido;

		
	}
	
	public Integer getQuantidadeTotalItensCarrinho() {
		return carrinho.getTotalUnidadeItemCarrinho();
	}
	

	public Integer getQuantidadePorItemCarrinho(ServicoItemCarrinho item) {
		return carrinho.getQuantidadeItemCarrinho(item);
	}

	public void esvaziarCarrinho() {
		this.carrinho.esvaziarCarrinho();
	}

	public BigDecimal getValorTotalPorServicoCarrinho(ServicoItemCarrinho item) {
		return this.carrinho.getTotalUnidadeServicoCarrinho(item);
	}

	public BigDecimal getValorTotalCarrinho() {
		return carrinho.getPrecoTotalCarrinho();
	}
	
	public List<ServicoItemCarrinhoDTO> finalizarAtendimentoItensCarrinho() {
		List<ServicoItemCarrinhoDTO> servicosCarrinho = getServicosCarrinho();
		return servicosCarrinho;
	}

}
