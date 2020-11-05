package br.com.salon.carine.lima.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.salon.carine.lima.enuns.StatusAtendimento;
import br.com.salon.carine.lima.enuns.TipoEndereco;

@Entity
@NamedEntityGraph(name = "Atendimento.itens", 
attributeNodes = @NamedAttributeNode("itens"))
public class Atendimento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Transient
	private Integer rowNumber;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	private BigDecimal totalBruto;
	
	private BigDecimal totalLiquido;
	
	@Temporal(TemporalType.DATE)
	private Calendar data;
	
	private Time hora;
	
	@OneToOne
	@JoinColumn(name = "endereco_id")
	private Endereco endereco;

	@Enumerated(EnumType.STRING)
	private StatusAtendimento status;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pagamento_id")
	private Pagamento pagamento;

	private BigDecimal desconto;

	private BigDecimal taxa;
	
	@Enumerated(EnumType.STRING)
	private TipoEndereco tipoEndereco;
	
	@JsonIgnore
	@OneToMany(mappedBy = "atendimento", cascade = CascadeType.REMOVE)
	private List<ServicoItemCarrinho> itens = new ArrayList<>();

	public Atendimento() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public BigDecimal getTotalBruto() {
		return totalBruto;
	}

	public void setTotalBruto(BigDecimal totalBruto) {
		this.totalBruto = totalBruto;
	}

	public BigDecimal getTotalLiquido() {
		return totalLiquido;
	}

	public void setTotalLiquido(BigDecimal totalLiquido) {
		this.totalLiquido = totalLiquido;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public Time getHora() {
		return hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public StatusAtendimento getStatus() {
		return status;
	}

	public void setStatus(StatusAtendimento status) {
		this.status = status;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public BigDecimal getDesconto() {
		return desconto;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}

	public List<ServicoItemCarrinho> getItens() {
		return itens;
	}

	public void setItens(List<ServicoItemCarrinho> itens) {
		this.itens = itens;
	}

	public BigDecimal getTaxa() {
		return taxa;
	}

	public void setTaxa(BigDecimal taxa) {
		this.taxa = taxa;
	}

	public TipoEndereco getTipoEndereco() {
		return tipoEndereco;
	}

	public void setTipoEndereco(TipoEndereco tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
	}
	
	public Integer getRowNumber() {
		return rowNumber;
	}
	
	public void setRowNumber(Integer rowNumber) {
		this.rowNumber = rowNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atendimento other = (Atendimento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
