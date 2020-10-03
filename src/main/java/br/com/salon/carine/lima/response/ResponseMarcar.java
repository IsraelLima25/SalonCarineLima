package br.com.salon.carine.lima.response;

import java.io.Serializable;

import br.com.salon.carine.lima.dto.MarcarAtendimentoDTO;

public class ResponseMarcar implements Serializable {

	private static final long serialVersionUID = 1L;

	private MarcarAtendimentoDTO atendimento;
	private Message message;

	public ResponseMarcar() {
		
	}
	
	public ResponseMarcar(MarcarAtendimentoDTO atendimento, Message message) {
		this.atendimento = atendimento;
		this.message = message;
	}

	public MarcarAtendimentoDTO getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(MarcarAtendimentoDTO atendimento) {
		this.atendimento = atendimento;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

}
