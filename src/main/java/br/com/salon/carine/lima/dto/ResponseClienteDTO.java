package br.com.salon.carine.lima.dto;

public class ResponseClienteDTO {

	private ClienteDTO cliente;
	private MessageDTO message;

	public ResponseClienteDTO() {
	}

	public ResponseClienteDTO(ClienteDTO cliente, MessageDTO message) {
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

	public MessageDTO getMessage() {
		return message;
	}

	public void setMessage(MessageDTO message) {
		this.message = message;
	}

}
