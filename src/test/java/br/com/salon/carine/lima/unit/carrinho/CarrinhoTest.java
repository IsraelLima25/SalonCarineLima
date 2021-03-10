package br.com.salon.carine.lima.unit.carrinho;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.salon.carine.lima.dto.ServicoItemCarrinhoDTO;
import br.com.salon.carine.lima.enuns.StatusAtendimento;
import br.com.salon.carine.lima.enuns.TipoEndereco;
import br.com.salon.carine.lima.models.Carrinho;
import br.com.salon.carine.lima.models.Cliente;
import br.com.salon.carine.lima.models.Debito;
import br.com.salon.carine.lima.models.Endereco;
import br.com.salon.carine.lima.models.Servico;
import br.com.salon.carine.lima.models.ServicoItemCarrinho;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { Carrinho.class })
public class CarrinhoTest {

	@Autowired
	private Carrinho carrinho;

	private List<ServicoItemCarrinho> itens = new ArrayList<>();

	@Before
	public void carregarCarrinho() {
		
		this.itens.add((new BuilderServicoItemCarrinho().servico(new Servico(1, "Corte Cabelo", BigDecimal.valueOf(40.00)))
				.quantidade(3).precoAtual(BigDecimal.valueOf(40.00))
				.id(1)
				.atendimento(new BuilderAtendimento()
						.cliente(new Cliente("Luan Carlos"))
						.data(Calendar.getInstance())
						.desconto(BigDecimal.valueOf(5.00))
						.endereco(new Endereco("4265878")).hora(null)
						.pagamento(new Debito())
						.status(StatusAtendimento.PENDENTE)
						.taxa(BigDecimal.ZERO)
						.tipoEndereco(TipoEndereco.CASA)
						.totalBruto(BigDecimal.valueOf(40.00))
						.totalLiquido(BigDecimal.valueOf(35.00)).build())
				.build()));
		
		this.itens.add((new BuilderServicoItemCarrinho().servico(new Servico(2, "Desenho Cabelo", BigDecimal.valueOf(35.00)))
				.quantidade(2).precoAtual(BigDecimal.valueOf(35.00))
				.id(2)
				.atendimento(new BuilderAtendimento()
						.cliente(new Cliente("sizenando Carlos"))
						.data(Calendar.getInstance())
						.desconto(BigDecimal.valueOf(5.00))
						.endereco(new Endereco("4265887"))
						.hora(null)
						.pagamento(new Debito())
						.status(StatusAtendimento.PENDENTE)
						.taxa(BigDecimal.ZERO)
						.tipoEndereco(TipoEndereco.CASA)
						.totalBruto(BigDecimal.valueOf(35.00))
						.totalLiquido(BigDecimal.valueOf(35.00)).build())
				.build()));

		this.carrinho.adicionarItemCarrinho(this.itens.get(0));
		this.carrinho.adicionarItemCarrinho(this.itens.get(0));
		this.carrinho.adicionarItemCarrinho(this.itens.get(0));
		
		
		this.carrinho.adicionarItemCarrinho(this.itens.get(1));
		this.carrinho.adicionarItemCarrinho(this.itens.get(1));
		this.carrinho.adicionarItemCarrinho(this.itens.get(1));
		this.carrinho.adicionarItemCarrinho(this.itens.get(1));
	}
	
	@After
	public void limparCarrinho() {
		this.carrinho.esvaziarCarrinho();
	}
	
	@Test
	public void deveRemoverAQuantidadeDeDuasUnidadeParaPrimeiroItemAdicionadoCarrinho() {
		ServicoItemCarrinhoDTO itemAtualizado = this.carrinho.removerUnidadeItemCarrinho(this.itens.get(0));
		assertEquals(2, (int) itemAtualizado.getQuantidade());
	}
	
	@Test
	public void deveAdicionarItemCarrinhoERetornarQuantidadeAtualizada() {
		
		this.itens.add((new BuilderServicoItemCarrinho().servico(new Servico(2, "Lavagem Cabelo", BigDecimal.valueOf(35.00)))
				.quantidade(4).precoAtual(BigDecimal.valueOf(35.00))
				.id(3)
				.atendimento(new BuilderAtendimento()
						.cliente(new Cliente("sizenando Carlos"))
						.data(Calendar.getInstance())
						.desconto(BigDecimal.valueOf(5.00))
						.endereco(new Endereco("4265887"))
						.hora(null)
						.pagamento(new Debito())
						.status(StatusAtendimento.PENDENTE)
						.taxa(BigDecimal.ZERO)
						.tipoEndereco(TipoEndereco.CASA)
						.totalBruto(BigDecimal.valueOf(35.00))
						.totalLiquido(BigDecimal.valueOf(35.00)).build())
				.build()));
		
		this.carrinho.adicionarItemCarrinho(this.itens.get(2));
		
		int quantidadeTotal = this.carrinho.getTotalUnidadeItemCarrinho();
		assertEquals(8, (int) quantidadeTotal);
	}
	
	@Test
	public void deveRetornarAQuantidadeUnidade3ParaItemId1() {
		Integer quantidadeItem = this.carrinho.getQuantidadeItemCarrinho(this.itens.get(0));
		assertEquals(3, (int) quantidadeItem);
	}
	
	@Test
	public void deveRetornarCarrinhoVazio() {
		this.carrinho.esvaziarCarrinho();
		int quantidadeTotal = this.carrinho.getTotalUnidadeItemCarrinho();
		assertEquals(0, (int) quantidadeTotal);
	}
	
	@Test
	public void deveRetornarQuantidadeTotalItens() {
		int quantidadeTotal = this.carrinho.getTotalUnidadeItemCarrinho();
		assertEquals(7, (int) quantidadeTotal);
	}
	
	@Test
	public void deveRetornarPrecoTotalItem() {
		BigDecimal precoTotalServico = this.carrinho.getTotalUnidadeServicoCarrinho(this.itens.get(0));
		assertTrue(precoTotalServico.equals(BigDecimal.valueOf(120.00)));
	}
	
	@Test
	public void deveRetornarPrecoTotalCarrinho() {
		BigDecimal precoTotalCarrinho = this.carrinho.getPrecoTotalCarrinho();
		assertTrue(precoTotalCarrinho.equals(BigDecimal.valueOf(260.00)));
	}
	
	@Test
	public void deveRemoverItemCarrinhoCarrinho() {
		boolean itemRemovido = this.carrinho.removerItemCarrinho(this.itens.get(0));
		assertTrue(itemRemovido);
		int quantidadeTotalUnidadeItemCarrinho = this.carrinho.getTotalUnidadeItemCarrinho();
		assertEquals(4, quantidadeTotalUnidadeItemCarrinho);
		
	}
}
