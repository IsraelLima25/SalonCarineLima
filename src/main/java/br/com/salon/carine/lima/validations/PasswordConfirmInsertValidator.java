package br.com.salon.carine.lima.validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordConfirmValidator.class)
public @interface PasswordConfirmInsertValidator {

	String message() default "Confirmação de senha inválida";
	
	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
	
	String value() default "";

}
