package com.gsuna.project.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.gsuna.project.annotation.impl.UniqueUsernameValidator;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { UniqueUsernameValidator.class })
public @interface UniqueUsername {
	

	String message() default "Username must be unique";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}
