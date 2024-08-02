package com.example.asswrkspt.web.validators;

import static com.example.asswrkspt.common.constants.Constants.FN_LESS_THAN_3;
import static com.example.asswrkspt.common.constants.Constants.LN_LESS_THAN_3;
import static com.example.asswrkspt.common.constants.Constants.MOBILE_NUMBER_PATTERN;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import com.example.asswrkspt.web.dto.request.CustomerRequestDTO;

@Component
public class CustomerCreateRequestValidator implements ConstraintValidator<ValidCustomerRequest, CustomerRequestDTO> {

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

        if (!isMobileValid(customerRequestDTO.getMobileNumber())) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(LN_LESS_THAN_3).addConstraintViolation();
            return false;
        }

        return true;
    }

    private boolean isMobileValid(String mobileNumber) {
        Pattern pattern = Pattern.compile(MOBILE_NUMBER_PATTERN);
        Matcher matcher = pattern.matcher(mobileNumber);
        return matcher.matches();
    }
}
