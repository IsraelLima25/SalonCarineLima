package br.com.salon.carine.lima.response;

import br.com.salon.carine.lima.dto.ClienteDTO;

public class ResponseCliente {

	private ClienteDTO cliente;
	private Message message;

	public ResponseCliente() {
	}

	public ResponseCliente(ClienteDTO cliente, Message message) {
		super();
		this.cliente = cliente;
		this.message = message;
	}

	public ClienteDTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

}
