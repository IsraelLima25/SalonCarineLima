package br.com.salon.carine.lima.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.salon.carine.lima.converters.ConvertersServico;
import br.com.salon.carine.lima.dto.ServicoItemCarrinhoDTO;

@Component
@Scope("singleton")
public class Carrinho implements Serializable {

	private static final long serialVersionUID = 1L;

	private Map<ServicoItemCarrinho, Integer> servicos = new HashMap<>();
	
	public Map<ServicoItemCarrinho, Integer> getServicos() {
		return servicos;
	}

	public ServicoItemCarrinhoDTO add(ServicoItemCarrinho itemServico) {
		
		servicos.put(itemServico, getQuantidadeItem(itemServico) + 1);		
		ServicoItemCarrinhoDTO itemAdicionado = getItemCarrinho(itemServico);
		
		return itemAdicionado;
	}
	
	public ServicoItemCarrinhoDTO removerItemCarrinho(ServicoItemCarrinho itemServico) {	
		
		Integer quantidade = servicos.get(itemServico);
		
		if(quantidade == 1) {
			ServicoItemCarrinhoDTO item = getItemCarrinho(itemServico);
			return item;
		}else {
			quantidade--;
			servicos.put(itemServico, quantidade);
			ServicoItemCarrinhoDTO itemRemovido = getItemCarrinho(itemServico);
			return itemRemovido;
		}
		
	}
	
	public ServicoItemCarrinhoDTO getItemCarrinho(ServicoItemCarrinho itemServico) {
		
		ServicoItemCarrinhoDTO servicoItemCarrinhoDTO = new ServicoItemCarrinhoDTO();
		servicoItemCarrinhoDTO.setServicoDTO(ConvertersServico.deServicoParaServicoDTO(itemServico.getServico()));
		servicoItemCarrinhoDTO.setQuantidade(getQuantidadeItem(itemServico));
		servicoItemCarrinhoDTO.setPrecoTotal(getTotalServico(itemServico));
		
		return servicoItemCarrinhoDTO;
	}
	
	public Integer getQuantidadeItem(ServicoItemCarrinho itemServico) {
		if (!servicos.containsKey(itemServico)) {
			servicos.put(itemServico, 0);
		}
		return servicos.get(itemServico);
	}	

	public void esvaziar() {
		this.servicos.clear();
	}

	public int getQuantidadeTotal() {
		return this.servicos.values().stream().reduce(0, (proximo, acumulador) -> proximo + acumulador);
	}

	public BigDecimal getTotalServico(ServicoItemCarrinho itemServico) {
		return itemServico.getTotal(getQuantidadeItem(itemServico));
	}

	public BigDecimal getTotalCarrinho() {
		BigDecimal total = BigDecimal.ZERO;

		for (ServicoItemCarrinho itemServico : servicos.keySet()) {
			total = total.add(getTotalServico(itemServico));
		}
		return total;
	}
	
	public boolean removerTodoItem(ServicoItemCarrinho itemServico) {		
		if(itemServico != null) {			
			this.servicos.remove(itemServico);	
			return true;
		}else{
			return false;
		}
	}
	
}
