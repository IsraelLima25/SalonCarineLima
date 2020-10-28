package br.com.salon.carine.lima.dto;

import java.io.Serializable;
import java.util.Calendar;

public class CalendarDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Calendar de;
	private Calendar para;
	
	public CalendarDTO() {
		
	}

	public CalendarDTO(Calendar de, Calendar para) {
		super();
		this.de = de;
		this.para = para;
	}

	public Calendar getDe() {
		return de;
	}

	public void setDe(Calendar de) {
		this.de = de;
	}

	public Calendar getPara() {
		return para;
	}

	public void setPara(Calendar para) {
		this.para = para;
	}
}
