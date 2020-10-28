package br.com.salon.carine.lima.validations;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.salon.carine.lima.converters.ConvertersDate;
import br.com.salon.carine.lima.dto.FiltroDataAtendimentoDTO;
import br.com.salon.carine.lima.exceptions.FieldMessage;

public class FilterDateValidator implements ConstraintValidator<DateFilterValidator, FiltroDataAtendimentoDTO>{

	List<FieldMessage> list = new ArrayList<>();
	
	@Override
	public boolean isValid(FiltroDataAtendimentoDTO filtro, ConstraintValidatorContext context) {
		
		list.clear();
		
		Calendar calendarDateInicio = ConvertersDate.deStringDateParaCalendar(filtro.getDataInicio());
		Calendar calendarDateFim = ConvertersDate.deStringDateParaCalendar(filtro.getDataFim());
		
		if(filtro.getDataInicio().equals("") || calendarDateInicio.after(calendarDateFim)) {
			list.add(new FieldMessage("dataInicio","Data ínicio inválida: A data ínicio não pode ser vazia e"
					+ " não pode ser maior que a data fim."));
		}
		
		if(filtro.getDataFim().equals("") || calendarDateFim.before(calendarDateInicio)) {
			list.add(new FieldMessage("dataFim","Data fim inválida: A data fim não pode ser vazia e"
					+ " não pode ser menor que a data ínicio."));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage())
				.addPropertyNode(e.getFieldName())
				.addConstraintViolation();
		}

		return list.isEmpty();
	}

}
