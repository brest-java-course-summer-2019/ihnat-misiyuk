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
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml"})
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
        assertTrue(rental.getRentalRate().equals("DAILY"));
    }

    @Test
    public void add() {
        List<Rental> rentals = rentalDao.findAll();
        int sizeBefore = rentals.size();
        Rental rental = new Rental("NEW", 1);
        Rental newRental = rentalDao.add(rental);
        assertNotNull(newRental.getRentalId());
        assertTrue(newRental.getRentalRate().equals(rental.getRentalRate()));
        assertTrue(newRental.getCarId().equals(rental.getCarId()));
        assertTrue((sizeBefore + 1) == rentalDao.findAll().size());
    }

    @Test
    public void update() {
        Rental rental = rentalDao.findById(1).get();
        rental.setRentalRate("MAIN");
        rentalDao.update(rental);
        Rental updatedRental = rentalDao.findById(rental.getRentalId()).get();
        assertTrue(updatedRental.getRentalId().equals(rental.getRentalId()));
        assertTrue(updatedRental.getRentalRate().equals(rental.getRentalRate()));
    }

    @Test
    public void delete() {
        Rental rental = new Rental("MAIN", 1);
        rental.setRentalId(10);
        rentalDao.add(rental);
        List<Rental> rentals = rentalDao.findAll();
        int sizeBefore = rentals.size();
        rentalDao.delete(rental.getRentalId());
        assertTrue((sizeBefore - 1) == rentalDao.findAll().size());
    }
}














