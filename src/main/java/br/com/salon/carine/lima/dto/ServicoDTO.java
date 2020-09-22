package br.com.salon.carine.lima.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class ServicoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@Length(min = 1, max = 25, message = "Descrição Obrigatória")
	private String descricao;
	
	@NotNull(message = "Preço Obrigatório")
	@Digits(integer = 3, fraction = 2, message = "Preço inválido")
	private BigDecimal preco;

	public ServicoDTO() {

	}

	public ServicoDTO(Integer id, String descricao, BigDecimal preco) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.preco = preco;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

}
