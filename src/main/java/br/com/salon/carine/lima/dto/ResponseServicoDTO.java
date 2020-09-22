package br.com.salon.carine.lima.dto;

public class ResponseServicoDTO {

	private ServicoDTO servico;
	private MessageDTO message;

	public ResponseServicoDTO() {

	}

	public ResponseServicoDTO(ServicoDTO cliente, MessageDTO message) {
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

	public MessageDTO getMessage() {
		return message;
	}

	public void setMessage(MessageDTO message) {
		this.message = message;
	}

}
