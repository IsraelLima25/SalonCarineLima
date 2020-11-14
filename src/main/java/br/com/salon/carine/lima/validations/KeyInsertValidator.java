package br.com.salon.carine.lima.validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = KeyValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface KeyInsertValidator {
	
	String message()

	default "A chave de acesso fornecida é inválida";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
