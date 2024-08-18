package com.einstein.intelligence.util;

import com.einstein.intelligence.exception.SystemException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;


/**
 * @author ZhangChunjie
 */
public class ValidatorUtils {

    private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

    public static void validateEntity(Object object, Class<?>... groups) {
        Set<ConstraintViolation<Object>> constraintViolations = VALIDATOR.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            ConstraintViolation<Object> constraint = constraintViolations.iterator().next();
            throw new SystemException(constraint.getMessage());
        }
    }

    private ValidatorUtils() {
        //空参构造
    }

}
