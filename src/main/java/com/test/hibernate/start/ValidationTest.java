package com.test.hibernate.start;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.test.hibernate.entities.Author;

public class ValidationTest {

	public static void main(String[] args) {
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		Validator validator = validatorFactory.getValidator();

		Author author = new Author();

		Set<ConstraintViolation<Author>> coSet = validator.validate(author);

		for (ConstraintViolation<Author> coViolation : coSet)
			System.out.println(String.format("property: %s value: %s message: %s", coViolation.getPropertyPath(), coViolation.getInvalidValue(), coViolation.getMessage()));
	}

}
