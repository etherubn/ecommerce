package com.comercio.demo.validations;

import com.comercio.demo.enums.CategoryType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class ExistedValidator implements ConstraintValidator<ExistedOption, CategoryType> {

    @Override
    public boolean isValid(CategoryType value, ConstraintValidatorContext context) {
        return Arrays.stream(CategoryType.values())
                .anyMatch(option->option==value);
    }
}
