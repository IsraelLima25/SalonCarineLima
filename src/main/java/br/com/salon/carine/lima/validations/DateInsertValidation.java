package br.com.salon.carine.lima.validations;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.salon.carine.lima.converters.ConvertersDate;
import br.com.salon.carine.lima.exceptions.FieldMessage;

public class DateInsertValidation implements ConstraintValidator<DateInsert, String> {
	
	List<FieldMessage> list = new ArrayList<>();

	@Override
	public boolean isValid(String data, ConstraintValidatorContext context) {
		
		Calendar date = ConvertersDate.deStringDateParaCalendar(data);
		
		if(date.before(Calendar.getInstance())) {
			list.add(new FieldMessage(null, "Data anterior a atual não permitida"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		
		return list.isEmpty();
		
	}

}
