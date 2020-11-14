package br.com.salon.carine.lima.dto;

import java.io.Serializable;

public class EmailDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String assunto;
	
	@SuppressWarnings("unused")
	private String remetente;
	
	private String destinatario;
	private String mensagem;

	public EmailDTO() {

	}

	public EmailDTO(String assunto, String remetente, String destinatario, String mensagem) {
		this.assunto = assunto;
		this.remetente = remetente;
		this.destinatario = destinatario;
		this.mensagem = mensagem;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getRemetente() {
		return "sagsoftwareagendamentos@gmail.com";
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
