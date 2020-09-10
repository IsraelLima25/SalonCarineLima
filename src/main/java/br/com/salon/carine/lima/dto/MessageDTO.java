package br.com.salon.carine.lima.dto;

public class MessageDTO {

	private String title;
	private String body;

	public MessageDTO() {

	}

	public MessageDTO(String title, String body) {
		this.title = title;
		this.body = body;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getBody() {
		return body;
	}
}
