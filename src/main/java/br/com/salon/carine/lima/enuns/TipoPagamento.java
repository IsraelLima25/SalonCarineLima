package br.com.salon.carine.lima.enuns;

public enum TipoPagamento {

	CREDITO(1, "Crédito"), DEBITO(2, "Débito"), ESPECIE(3, "Espécie");

	private Integer codigo;
	private String descricao;

	TipoPagamento(int codigo, String descricao) {
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
