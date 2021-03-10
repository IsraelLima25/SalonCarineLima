package br.com.salon.carine.lima.unit.carrinho;

import java.math.BigDecimal;

import br.com.salon.carine.lima.models.Atendimento;
import br.com.salon.carine.lima.models.Servico;
import br.com.salon.carine.lima.models.ServicoItemCarrinho;

public class BuilderServicoItemCarrinho {

	private ServicoItemCarrinho item;

	public BuilderServicoItemCarrinho() {
		this.item = new ServicoItemCarrinho();
	}
	
	public BuilderServicoItemCarrinho id(Integer id) {

		this.item.setId(id);
		return this;
	}

	public BuilderServicoItemCarrinho quantidade(Integer quantidade) {

		this.item.setQuantidade(quantidade);
		return this;
	}
	
	public BuilderServicoItemCarrinho precoAtual(BigDecimal precoAtual) {

		this.item.setPrecoAtual(precoAtual);
		return this;
	}
	
	public BuilderServicoItemCarrinho servico(Servico servico) {

		this.item.setServico(servico);
		return this;
	}

	public BuilderServicoItemCarrinho atendimento(Atendimento atendimento) {
		this.item.setAtendimento(atendimento);
		return this;
	}

	public ServicoItemCarrinho build() {
		return this.item;
	}

}
