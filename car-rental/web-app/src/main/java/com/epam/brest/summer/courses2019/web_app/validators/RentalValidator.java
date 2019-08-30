package com.epam.brest.summer.courses2019.web_app.validators;

import com.epam.brest.summer.courses2019.model.Rental;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class RentalValidator implements Validator {

    public static final int RENTAL_RATE_MAX_SIZE = 255;

    @Override
    public boolean supports(Class<?> aClass) {
        return Rental.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmpty(errors, "rentalRate", "rentalBrand.empty");
        Rental rental = (Rental) target;

        if (StringUtils.hasLength(rental.getRentalRate())
                && rental.getRentalRate().length() > RENTAL_RATE_MAX_SIZE) {
            errors.rejectValue("rentalRate", "rentalRate.maxSize255");
        }
    }
}
