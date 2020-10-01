package br.com.salon.carine.lima.enuns;

public enum FormaPagamento {
	
	ESPECIE(0,"especie"), CREDITO(1,"credito"), DEBITO(2,"debito");
	
	private Integer codigo;
	private String descricao;

	private FormaPagamento(Integer codigo, String descricao) {
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

	public static FormaPagamento getFormaPagamento(String descricao) {

		if (descricao == null) {
			return null;
		}

		for (FormaPagamento f : FormaPagamento.values()) {
			if (descricao.equals(f.getDescricao())) {
				return f;
			}
		}

		throw new IllegalArgumentException("Forma Pagamento Inválido " + descricao);

	}
	
	
}
