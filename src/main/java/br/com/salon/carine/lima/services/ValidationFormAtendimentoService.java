package br.com.salon.carine.lima.services;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import br.com.salon.carine.lima.dto.MarcarAtendimentoDTO;
import br.com.salon.carine.lima.exceptions.ArgumentNotValidException;

@Service
public class ValidationFormAtendimentoService implements Serializable {

	private static final long serialVersionUID = 1L;

	public void isFormNotValid(List<String> fieldsNotValid, BindingResult result,
			MarcarAtendimentoDTO atendimentoDTO, HttpServletRequest request) {

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		Set<ConstraintViolation<MarcarAtendimentoDTO>> violations = validator.validate(atendimentoDTO);

		if (violations.size() > 0) {
			for (ConstraintViolation<MarcarAtendimentoDTO> violation : violations) {
				System.out.println(violation.getPropertyPath().toString() + violation.getMessage());
				FieldError fieldError = new FieldError("", violation.getPropertyPath().toString(),
						violation.getMessage());
				result.addError(fieldError);
			}
			
			BindingResult resultFilter = filterErrors(result, fieldsNotValid);

			throw new ArgumentNotValidException(resultFilter, request);
		}

	}

	@SuppressWarnings("unlikely-arg-type")
	private BindingResult filterErrors(BindingResult result, List<String> fieldsNotValid) {
		
		if(fieldsNotValid != null) {
			fieldsNotValid.forEach(field -> 
			result.getFieldErrors().remove(field));
			return result;
		}else {
			return result;
		}
		
	}

}
