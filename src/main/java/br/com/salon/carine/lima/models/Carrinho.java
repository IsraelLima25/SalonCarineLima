package br.com.salon.carine.lima.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class Carrinho implements Serializable {

	private static final long serialVersionUID = 1L;

	private Map<ServicoItemCarrinho, Integer> servicos = new HashMap<>();

	public Collection<ServicoItemCarrinho> getServicos() {
		return servicos.keySet();
	}

	public void add(ServicoItemCarrinho itemServico) {
		servicos.put(itemServico, getQuantidadeItem(itemServico) + 1);
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
	
	public void removeItem(ServicoItemCarrinho itemServico) {		
		this.servicos.remove(itemServico);		
	}
	
	public void decrementarQuantidadeItem(ServicoItemCarrinho itemServico) {	
		
		Integer quantidade = servicos.get(itemServico);
		
		if(quantidade == 0) {
			removeItem(itemServico);			
		}else {
			quantidade--;
			servicos.put(itemServico, quantidade);
		}
		
	}
}
