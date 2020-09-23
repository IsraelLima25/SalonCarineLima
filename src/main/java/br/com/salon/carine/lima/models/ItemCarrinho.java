package br.com.salon.carine.lima.models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ItemCarrinho implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Map<Integer, Servico> item = new HashMap<Integer, Servico>();

	public ItemCarrinho(Integer quantidade, Servico servico) {
		this.item.put(quantidade, servico);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Map<Integer, Servico> getItem() {
		return item;
	}

	public void setItem(Map<Integer, Servico> item) {
		this.item = item;
	}

}
