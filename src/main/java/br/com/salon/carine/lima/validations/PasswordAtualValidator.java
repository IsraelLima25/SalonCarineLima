package br.com.salon.carine.lima.validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = PasswordAtualInsertValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordAtualValidator {
	
	String message() default "Senha atual inválida";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
