package com.epam.brest.summer.courses2019.web_app.validators;

import com.epam.brest.summer.courses2019.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.thymeleaf.util.StringUtils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CarValidatorTest {

    Car car;

    CarValidator carValidator = new CarValidator();
    BindingResult result;

    @BeforeEach
    void setup() {
        car = Mockito.mock(Car.class);
        result = new BeanPropertyBindingResult(car, "car");
    }

    @Test
    void shouldRejectNullCarBrand() {
        // given
        Mockito.when(car.getCarBrand()).thenReturn(null);

        // when
        carValidator.validate(car, result);

        // then
        assertTrue(result.hasErrors());
    }

    @Test
    void shouldRejectEmptyCarBrand() {
        // given
        Mockito.when(car.getCarBrand()).thenReturn("");

        // when
        carValidator.validate(car, result);

        // then
        assertTrue(result.hasErrors());
    }

    @Test
    void shouldRejectLargeCarBrand() {

        // given
        String filled = StringUtils.repeat("*", 300);
        Mockito.when(car.getCarBrand()).thenReturn(filled);

        // when
        carValidator.validate(car, result);

        // then
        assertTrue(result.hasErrors());
    }

    @Test
    void shouldValidateCarBrand() {

        //given
        String filled = StringUtils.repeat("*", 250);
        Mockito.when(car.getCarBrand()).thenReturn(filled);

        // when
        carValidator.validate(car, result);

        // then
        assertFalse(result.hasErrors());
    }
}












