package br.com.salon.carine.lima.enuns;

public enum StatusAtendimento {

	ATENDIDO(1, "Atendido"), PENDENTE(2, "Pendente");

	private Integer codigo;
	private String descricao;

	private StatusAtendimento(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
