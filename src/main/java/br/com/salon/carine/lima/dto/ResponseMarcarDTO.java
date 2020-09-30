package br.com.salon.carine.lima.dto;

import java.io.Serializable;

public class ResponseMarcarDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private MarcarAtendimentoDTO atendimento;
	private MessageDTO message;

	public ResponseMarcarDTO() {
		
	}
	
	public ResponseMarcarDTO(MarcarAtendimentoDTO atendimento, MessageDTO message) {
		this.atendimento = atendimento;
		this.message = message;
	}

	public MarcarAtendimentoDTO getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(MarcarAtendimentoDTO atendimento) {
		this.atendimento = atendimento;
	}

	public MessageDTO getMessage() {
		return message;
	}

	public void setMessage(MessageDTO message) {
		this.message = message;
	}

}
