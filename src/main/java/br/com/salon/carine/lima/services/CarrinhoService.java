package br.com.salon.carine.lima.services;

import java.math.BigDecimal;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.salon.carine.lima.converters.ConvertersServico;
import br.com.salon.carine.lima.dto.ServicoDTO;
import br.com.salon.carine.lima.models.Carrinho;
import br.com.salon.carine.lima.models.Servico;
import br.com.salon.carine.lima.models.ServicoItemCarrinho;

@Service
public class CarrinhoService {

	@Autowired
	private Carrinho carrinho;

	public Collection<ServicoItemCarrinho> addServicoCarrinho(ServicoDTO servicoDTO) {

		Servico servico = ConvertersServico.deServicoDTOparaServico(servicoDTO);
		ServicoItemCarrinho servicoCarrinho = new ServicoItemCarrinho();
		servicoCarrinho.setServico(servico);

		carrinho.add(servicoCarrinho);

		Collection<ServicoItemCarrinho> servicosCarrinho = carrinho.getServicos();

		return servicosCarrinho;

	}

	public Integer getQuantidadeTotalItensCarrinho() {
		return carrinho.getQuantidadeTotal();
	}
	
	public void decrementarQuantidadeItemCarrinho(ServicoItemCarrinho item) {
		this.carrinho.decrementarQuantidadeItem(item);
	}

	public Integer getQuantidadePorItemCarrinho(ServicoItemCarrinho item) {
		return carrinho.getQuantidadeItem(item);
	}

	public void esvaziarCarrinho() {
		this.carrinho.esvaziar();
	}

	public BigDecimal getValorTotalPorServicoCarrinho(ServicoItemCarrinho item) {
		return this.carrinho.getTotalServico(item);
	}

	public BigDecimal getValorTotalCarrinho() {
		return carrinho.getTotalCarrinho();
	}
}
