package br.com.salon.carine.lima.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Carrinho implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	List<ItemCarrinho> itensCarinho = new ArrayList<ItemCarrinho>();

	public Carrinho() {
	}
	
	public void adicionarItemCarrinho(ItemCarrinho itemCarrinho) {		
		this.itensCarinho.add(itemCarrinho);		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<ItemCarrinho> getItensCarinho() {
		return itensCarinho;
	}

	public void setItensCarinho(List<ItemCarrinho> itensCarinho) {
		this.itensCarinho = itensCarinho;
	}

}
