package br.com.salon.carine.lima.enuns;

public enum BandeiraCartao {

	ELO(0,"elo"), MASTERCARD(1,"master-card"), VISA(2,"visa");
	
	private Integer codigo;
	private String descricao;

	private BandeiraCartao (Integer codigo, String descricao){
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

	public static BandeiraCartao getBandeiraCartao(String descricao) {
		if (descricao == null || descricao.equals("")) {
			return null;
		}

		for (BandeiraCartao b : BandeiraCartao.values()) {
			if (descricao.equals(b.getDescricao())) {
				return b;
			}
		}

		throw new IllegalArgumentException("Bandeira Inválida " + descricao);

	}
	
}
