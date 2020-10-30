package br.com.salon.carine.lima.dto;

import java.io.Serializable;

import br.com.salon.carine.lima.validations.DateFilterValidator;

@DateFilterValidator
public class FiltroDataAtendimentoDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String dataInicio;
	private String dataFim;
	
	public FiltroDataAtendimentoDTO() {
		
	}

	public FiltroDataAtendimentoDTO(String dataInicio, String dataFim) {
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
	}

	public String getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getDataFim() {
		return dataFim;
	}

	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}
	
}
