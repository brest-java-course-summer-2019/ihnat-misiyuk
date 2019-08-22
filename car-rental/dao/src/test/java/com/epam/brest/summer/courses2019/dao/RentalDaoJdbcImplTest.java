package com.epam.brest.summer.courses2019.dao;


import com.epam.brest.summer.courses2019.model.Rental;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:../resources/test-dao.xml"})
@Transactional
@Rollback
public class RentalDaoJdbcImplTest {

    @Autowired
    RentalDao rentalDao;

    @Test
    public void findAll() {
        List<Rental> rentals = rentalDao.findAll();
        assertNotNull(rentalDao);
        assertTrue(rentals.size() > 0);
    }

    @Test
    public void findByCarId() {
        List<Rental> rentals = rentalDao.findByCarId(1);
        assertNotNull(rentalDao);
        assertTrue(rentals.size() > 0);
    }

    @Test
    public void findById() {
        assertNotNull(rentalDao);
        Rental rental = rentalDao.findById(1).get();
        assertTrue(rental.getRentalId().equals(1));
        assertTrue(rental.getRentalDays().equals(3));
        assertTrue(rental.getRentalRate().equals("DAILY"));
        assertTrue(rental.getRentalPrice().equals(50));
    }

    @Test
    public void add() {
        List<Rental> rentals = rentalDao.findAll();
        int sizeBefore = rentals.size();
        Rental rental = new Rental(5, "NEW", 40, 1);
        Rental newRental = rentalDao.add(rental);
        assertNotNull(newRental.getRentalId());
        assertTrue(newRental.getRentalDays().equals(rental.getRentalDays()));
        assertTrue(newRental.getRentalRate().equals(rental.getRentalRate()));
        assertTrue(newRental.getRentalPrice().equals(rental.getRentalPrice()));
        assertTrue((sizeBefore + 1) == rentalDao.findAll().size());
    }

    @Test
    public void update() {
        Rental rental = rentalDao.findById(1).get();
        rental.setRentalRate("MAIN");
        rental.setRentalDays(4);
        rental.setRentalPrice(60);
        rentalDao.update(rental);
        Rental updatedRental = rentalDao.findById(rental.getRentalId()).get();
        assertTrue(updatedRental.getRentalId().equals(rental.getRentalId()));
        assertTrue(updatedRental.getRentalRate().equals(rental.getRentalRate()));
        assertTrue(updatedRental.getRentalPrice().equals(rental.getRentalPrice()));
    }

    @Test
    public void delete() {
        Rental rental = new Rental(7, "MAIN", 90, 1);
        rentalDao.add(rental);
        List<Rental> rentals = rentalDao.findAll();
        int sizeBefore = rentals.size();
        rentalDao.delete(rental.getRentalId());
        assertTrue((sizeBefore - 1) == rentalDao.findAll().size());
    }
}














