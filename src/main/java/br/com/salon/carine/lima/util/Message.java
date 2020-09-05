package br.com.salon.carine.lima.util;

import org.springframework.stereotype.Component;

import br.com.salon.carine.lima.enuns.TypeMessage;

@Component
public class Message {

	private TypeMessage type;
	private String classe;
	private String text;

	public Message() {
	}

	public Message(TypeMessage type, String classe, String text) {
		super();
		this.type = type;
		this.classe = classe;
		this.text = text;
	}

	public TypeMessage getType() {
		return type;
	}

	public void setType(TypeMessage type) {
		this.type = type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

}
