package com.epam.brest.summer.courses2019.web_app.validators;

import com.epam.brest.summer.courses2019.model.Car;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CarValidator implements Validator {

    public static final int CAR_NAME_MAX_SIZE = 255;

    @Override
    public boolean supports(Class<?> aClass) {
        return Car.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmpty(errors, "carBrand", "carBrand.empty");
        Car car = (Car) target;

        if (StringUtils.hasLength(car.getCarBrand())
                && car.getCarBrand().length() > CAR_NAME_MAX_SIZE) {
            errors.rejectValue("carBrand", "carBrand.maxSize255");
        }
    }
}
