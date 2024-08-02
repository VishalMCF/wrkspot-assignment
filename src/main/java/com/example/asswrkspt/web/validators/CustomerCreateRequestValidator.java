package com.example.asswrkspt.web.validators;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import com.example.asswrkspt.web.dto.request.CustomerRequestDTO;

@Component
public class CustomerCreateRequestValidator implements ConstraintValidator<ValidCustomerRequest, CustomerRequestDTO> {

    public static final String FN_LESS_THAN_3 = "first name size cannot be less than 3";
    public static final String LN_LESS_THAN_3 = "last name size cannot be less than 3";

    @Override
    public void initialize(ValidCustomerRequest constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(CustomerRequestDTO customerRequestDTO, ConstraintValidatorContext constraintValidatorContext) {

        if (!StringUtils.isEmpty(customerRequestDTO.getFirstName()) && customerRequestDTO.getFirstName().length() < 3) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(FN_LESS_THAN_3).addConstraintViolation();
            return false;
        }

        if (!StringUtils.isEmpty(customerRequestDTO.getLastName()) && customerRequestDTO.getLastName().length() < 3) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(LN_LESS_THAN_3).addConstraintViolation();
            return false;
        }

        return true;
    }
}
