package br.com.salon.carine.lima.carrinho;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Calendar;

import br.com.salon.carine.lima.enuns.StatusAtendimento;
import br.com.salon.carine.lima.enuns.TipoEndereco;
import br.com.salon.carine.lima.models.Atendimento;
import br.com.salon.carine.lima.models.Cliente;
import br.com.salon.carine.lima.models.Endereco;
import br.com.salon.carine.lima.models.Pagamento;

public class BuilderAtendimento {

	private Atendimento atendimento;

	public BuilderAtendimento() {
		this.atendimento = new Atendimento();
	}

	public BuilderAtendimento id(Integer id) {

		this.atendimento.setId(id);
		return this;
	}

	public BuilderAtendimento cliente(Cliente cliente) {

		this.atendimento.setCliente(cliente);
		return this;
	}

	public BuilderAtendimento totalBruto(BigDecimal totalBruto) {

		this.atendimento.setTotalBruto(totalBruto);
		return this;
	}

	public BuilderAtendimento totalLiquido(BigDecimal totalLiquido) {

		this.atendimento.setTotalLiquido(totalLiquido);
		return this;
	}

	public BuilderAtendimento data(Calendar data) {

		this.atendimento.setData(data);
		return this;
	}

	public BuilderAtendimento hora(Time hora) {

		this.atendimento.setHora(hora);
		return this;
	}

	public BuilderAtendimento endereco(Endereco endereco) {

		this.atendimento.setEndereco(endereco);
		return this;
	}

	public BuilderAtendimento status(StatusAtendimento status) {

		this.atendimento.setStatus(status);
		return this;
	}

	public BuilderAtendimento pagamento(Pagamento pagamento) {

		this.atendimento.setPagamento(pagamento);
		return this;
	}

	public BuilderAtendimento desconto(BigDecimal desconto) {

		this.atendimento.setDesconto(desconto);
		return this;
	}

	public BuilderAtendimento taxa(BigDecimal taxa) {

		this.atendimento.setTaxa(taxa);
		return this;
	}

	public BuilderAtendimento tipoEndereco(TipoEndereco tipoEndereco) {

		this.atendimento.setTipoEndereco(tipoEndereco);
		return this;
	}

	public Atendimento build() {
		return this.atendimento;
	}

}
