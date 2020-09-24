package br.com.salon.carine.lima.carrinho;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.salon.carine.lima.models.Carrinho;
import br.com.salon.carine.lima.models.Servico;
import br.com.salon.carine.lima.models.ServicoItemCarrinho;

public class CarrinhoTest {

	private Carrinho carrinho;
	private List<Servico> servicos = new ArrayList<>();

	@Before
	public void builderCarrinho() {
		this.carrinho = new Carrinho();
	}

	@Before
	public void builderServicos() {

		Servico servico1 = new Servico();
		servico1.setId(1);
		servico1.setDescricao("Rena");
		servico1.setPreco(BigDecimal.valueOf(30.00));

		Servico servico2 = new Servico();
		servico2.setId(2);
		servico2.setDescricao("Corte Cabelo");
		servico2.setPreco(BigDecimal.valueOf(50.00));

		Servico servico3 = new Servico();
		servico3.setId(3);
		servico3.setDescricao("Limpeza Pele");
		servico3.setPreco(BigDecimal.valueOf(15.75));

		servicos.add(servico1);
		servicos.add(servico2);
		servicos.add(servico3);

	}

	@Test
	public void adionarItemCarrinhoTest() {

		ServicoItemCarrinho servicoItemCarrinho1 = new ServicoItemCarrinho(this.servicos.get(0));
		this.carrinho.add(servicoItemCarrinho1);

		ServicoItemCarrinho servicoItemCarrinho2 = new ServicoItemCarrinho(this.servicos.get(1));
		this.carrinho.add(servicoItemCarrinho2);

		int quantidadeItensAdicionadoCarrinho = this.carrinho.getQuantidadeTotal();

		assertEquals(2, quantidadeItensAdicionadoCarrinho);
	}

	@Test
	public void quantidadeDeItensDoCarrinhoTest() {
		ServicoItemCarrinho servicoItemCarrinho1 = new ServicoItemCarrinho(this.servicos.get(0));
		this.carrinho.add(servicoItemCarrinho1);
		this.carrinho.add(servicoItemCarrinho1);

		int quantidadeItensItemCarrinho = this.carrinho.getQuantidadeItem(servicoItemCarrinho1);

		assertEquals(2, quantidadeItensItemCarrinho);

	}

	@Test
	public void esvaziarCarrinhoTest() {

		ServicoItemCarrinho servicoItemCarrinho1 = new ServicoItemCarrinho(this.servicos.get(0));
		this.carrinho.add(servicoItemCarrinho1);
		this.carrinho.add(servicoItemCarrinho1);

		this.carrinho.esvaziar();

		int quantidadeItensAdicionadoCarrinho = this.carrinho.getQuantidadeTotal();

		assertEquals(0, quantidadeItensAdicionadoCarrinho);

	}

	@Test
	public void valorServicoPorQuantidadeAdicionadaCarringoTest() {
		ServicoItemCarrinho servicoItemCarrinho1 = new ServicoItemCarrinho(this.servicos.get(0));
		this.carrinho.add(servicoItemCarrinho1);
		this.carrinho.add(servicoItemCarrinho1);

		BigDecimal totalServico = this.carrinho.getTotalServico(servicoItemCarrinho1);

		assertEquals(BigDecimal.valueOf(60.00), totalServico);
	}

	@Test
	public void valorTotalCarrinhoItemTest() {

		ServicoItemCarrinho servicoItemCarrinho1 = new ServicoItemCarrinho(this.servicos.get(0));
		this.carrinho.add(servicoItemCarrinho1);
		this.carrinho.add(servicoItemCarrinho1);

		ServicoItemCarrinho servicoItemCarrinho2 = new ServicoItemCarrinho(this.servicos.get(1));
		this.carrinho.add(servicoItemCarrinho2);
		this.carrinho.add(servicoItemCarrinho2);

		ServicoItemCarrinho servicoItemCarrinho3 = new ServicoItemCarrinho(this.servicos.get(2));
		this.carrinho.add(servicoItemCarrinho3);
		this.carrinho.add(servicoItemCarrinho3);

		BigDecimal totalCarrinho = this.carrinho.getTotalCarrinho();

		assertEquals("191.50", totalCarrinho.toString());
	}

	@Test
	public void decrementarQuantidadeItemCarrinhoTest() {
		ServicoItemCarrinho servicoItemCarrinho1 = new ServicoItemCarrinho(this.servicos.get(0));
		this.carrinho.add(servicoItemCarrinho1);
		this.carrinho.add(servicoItemCarrinho1);

		ServicoItemCarrinho servicoItemCarrinho2 = new ServicoItemCarrinho(this.servicos.get(1));
		this.carrinho.add(servicoItemCarrinho2);
		this.carrinho.add(servicoItemCarrinho2);

		this.carrinho.decrementarQuantidadeItem(servicoItemCarrinho1);
		this.carrinho.decrementarQuantidadeItem(servicoItemCarrinho1);
		this.carrinho.decrementarQuantidadeItem(servicoItemCarrinho1);
		this.carrinho.decrementarQuantidadeItem(servicoItemCarrinho2);
		this.carrinho.decrementarQuantidadeItem(servicoItemCarrinho2);

		int totalItens = this.carrinho.getQuantidadeTotal();

		assertEquals(-1, totalItens);
	}

	@Test
	public void removerItemCarrinho() {
		ServicoItemCarrinho servicoItemCarrinho1 = new ServicoItemCarrinho(this.servicos.get(0));
		this.carrinho.add(servicoItemCarrinho1);
		this.carrinho.add(servicoItemCarrinho1);

		this.carrinho.removeItem(servicoItemCarrinho1);

		int totalItens = this.carrinho.getQuantidadeTotal();
		assertEquals(0, totalItens);
	}

}
