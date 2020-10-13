package br.com.salon.carine.lima.response;

import java.io.Serializable;

import br.com.salon.carine.lima.dto.AtendimentoDTO;

public class ResponseAtendimento implements Serializable {

	private static final long serialVersionUID = 1L;

	private AtendimentoDTO atendimento;
	private Message message;

	public ResponseAtendimento() {
	}

	public ResponseAtendimento(AtendimentoDTO atendimento, Message message) {
		super();
		this.atendimento = atendimento;
		this.message = message;
	}

	public AtendimentoDTO getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(AtendimentoDTO atendimento) {
		this.atendimento = atendimento;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

}
