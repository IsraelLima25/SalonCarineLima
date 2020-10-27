package br.com.salon.carine.lima.converters;

import java.util.Calendar;
import java.util.GregorianCalendar;

import br.com.salon.carine.lima.dto.CalendarDTO;

public class ConvertersDate {

	public static Calendar deStringDateParaCalendar(String date) {

		if(!date.equals("")) {
			
			String[] data = date.split("-");
			
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, Integer.parseInt(data[0]));
			calendar.set(Calendar.MONTH, (Integer.parseInt(data[1]) - 1));
			calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(data[2]));
			
			return calendar;
		}
		
		return null;
	}
	
	/* Captura o primeiro dia do mês atual e o ultimo dia do mês */
	public static CalendarDTO filterMonthActual() {
		
		Calendar dataInicial = new GregorianCalendar();
		
		Calendar dataFinal = new GregorianCalendar();
		
		int firstDayOfMonth = dataInicial.getActualMinimum(Calendar.DAY_OF_MONTH);
		int lastDayOfMonth = dataInicial.getActualMaximum(Calendar.DAY_OF_MONTH) - 1;
		
		dataInicial.set(Calendar.DAY_OF_MONTH, firstDayOfMonth);

		dataFinal.set(Calendar.YEAR, dataFinal.get(Calendar.YEAR));
		dataFinal.set(Calendar.MONTH, dataFinal.get(Calendar.MONTH));
		dataFinal.set(Calendar.DAY_OF_MONTH, lastDayOfMonth);
		
		CalendarDTO calendarDTO = new CalendarDTO(dataInicial, dataFinal);
		
		return calendarDTO;
		
	}
}
