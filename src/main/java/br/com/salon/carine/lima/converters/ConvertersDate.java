package br.com.salon.carine.lima.converters;

import java.util.Calendar;

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
}
