package com.epam.brest.summer.courses2019.rest_app;

import com.epam.brest.summer.courses2019.model.Rental;
import com.epam.brest.summer.courses2019.service.RentalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class RentalRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RentalRestController.class);

    @Autowired
    private RentalService rentalService;

    @GetMapping(value = "/rentals")
    public Collection<Rental> findAll() {
        LOGGER.debug("get all rentals");
        return rentalService.findAll();
    }

    @GetMapping(value = "/rentals/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Rental findById(@PathVariable Integer id) {
        LOGGER.debug("find rental by id({})", id);
        return rentalService.findById(id);
    }

    @GetMapping(value = "/rentals/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Rental findByCarId(@PathVariable Integer CarId) {
        LOGGER.debug("find rental by car id({})", CarId);
        return rentalService.findById(CarId);
    }

    @PutMapping()
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void update(@RequestBody Rental rental) {
        LOGGER.debug("update rental({})", rental);
        rentalService.update(rental);
    }

    @DeleteMapping(value = "/cars/{id}")
    public void delete(@PathVariable("id") int id) {
        LOGGER.debug("delete rental ({})", id);
        rentalService.delete(id);
    }

    @PostMapping()
    public ResponseEntity<Rental> add(@RequestBody Rental rental) {
        LOGGER.debug("add rental({})", rental);
        Rental result = rentalService.add(rental);
        return new ResponseEntity<>(rental, HttpStatus.CREATED);
    }
}











