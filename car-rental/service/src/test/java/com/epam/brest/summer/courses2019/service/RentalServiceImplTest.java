package com.epam.brest.summer.courses2019.service;

import com.epam.brest.summer.courses2019.model.Rental;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:../../test/java/resources/test-service.xml"})
@Transactional
@Rollback
public class RentalServiceImplTest {

    @Autowired
    private RentalService rentalService;

    @Test
    void findAll() {
        List<Rental> rentals = rentalService.findAll();

        assertNotNull(rentals);
        assertFalse(rentals.isEmpty());
    }

    @Test
    void findById() {
        int id = 1;
        Rental rental = rentalService.findById(id);

        assertNotNull(rental);
        assertEquals("DAILY", rental.getRentalRate());
    }

    @Test
    void update() {
        int id = 2;
        Rental rental = create();
        rental.setRentalId(id);
        rentalService.update(rental);
        rental = rentalService.findById(id);

        assertNotNull(rental);
        assertEquals("rental", rental.getRentalRate());
    }

    @Test
    void delete() {
        int id = 3;
        rentalService.delete(id);
        assertThrows(RuntimeException.class, () -> rentalService.findById(id));
    }

    private Rental create() {
        Rental rental = new Rental();
        rental.setRentalId(1);
        rental.setRentalRate("rental");
        return rental;
    }
}







