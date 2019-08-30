package com.epam.brest.summer.courses2019.web_app.validators;

import com.epam.brest.summer.courses2019.model.Rental;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.thymeleaf.util.StringUtils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RentalValidatorTest {

    Rental rental;

    RentalValidator rentalValidator = new RentalValidator();
    BindingResult result;

    @BeforeEach
    void setup() {
        rental = Mockito.mock(Rental.class);
        result = new BeanPropertyBindingResult(rental, "rental");
    }

    @Test
    void shouldRejectNullRentalRate() {
        // given
        Mockito.when(rental.getRentalRate()).thenReturn(null);

        // when
        rentalValidator.validate(rental, result);

        // then
        assertTrue(result.hasErrors());
    }

    @Test
    void shouldRejectEmptyRentalRate() {
        // given
        Mockito.when(rental.getRentalRate()).thenReturn("");

        // when
        rentalValidator.validate(rental, result);

        // then
        assertTrue(result.hasErrors());
    }

    @Test
    void shouldRejectLargeRentalRate() {

        // given
        String filled = StringUtils.repeat("*", 300);
        Mockito.when(rental.getRentalRate()).thenReturn(filled);

        // when
        rentalValidator.validate(rental, result);

        // then
        assertTrue(result.hasErrors());
    }

    @Test
    void shouldValidateRentalRate() {

        //given
        String filled = StringUtils.repeat("*", 250);
        Mockito.when(rental.getRentalRate()).thenReturn(filled);

        // when
        rentalValidator.validate(rental, result);

        // then
        assertFalse(result.hasErrors());
    }
}
