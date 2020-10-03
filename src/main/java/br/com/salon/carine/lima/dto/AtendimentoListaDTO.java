package br.com.salon.carine.lima.dto;

import java.io.Serializable;

import br.com.salon.carine.lima.models.Cliente;

public class AtendimentoListaDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Cliente cliente;
	private String data;
	private String hora;
	
	public AtendimentoListaDTO() {
	}

	public AtendimentoListaDTO(Integer id, Cliente cliente, String data, String hora) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.data = data;
		this.hora = hora;
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

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}
}
