package br.com.salon.carine.lima.enuns;

public enum TipoEndereco {

	CASA(0, "casa"), ENDERECO_CLIENTE(1, "cliente"), OUTRO_ENDERECO(2, "outro");

	private Integer codigo;
	private String descricao;

	private TipoEndereco(Integer codigo, String descricao) {
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

	public static TipoEndereco getTipoEndereco(String descricao) {

		if (descricao == null) {
			return null;
		}

		for (TipoEndereco t : TipoEndereco.values()) {
			if (descricao.equals(t.getDescricao())) {
				return t;
			}
		}

		throw new IllegalArgumentException("Endereço Inválido " + descricao);

	}
}
