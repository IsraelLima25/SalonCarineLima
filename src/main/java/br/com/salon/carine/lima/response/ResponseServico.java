package br.com.salon.carine.lima.response;

import br.com.salon.carine.lima.dto.ServicoDTO;

public class ResponseServico {

	private ServicoDTO servico;
	private Message message;

	public ResponseServico() {

	}

	public ResponseServico(ServicoDTO cliente, Message message) {
		super();
		this.servico = cliente;
		this.message = message;
	}

	public ServicoDTO getServico() {
		return servico;
	}
	
	public void setServico(ServicoDTO servico) {
		this.servico = servico;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

}
