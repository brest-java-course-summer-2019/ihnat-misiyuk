package com.epam.brest.summer.courses2019.service;

import com.epam.brest.summer.courses2019.dao.RentalDao;
import com.epam.brest.summer.courses2019.model.Rental;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Rental Service Interface implementation.
 */
@Component
@Transactional
public class RentalServiceImpl implements RentalService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarServiceImpl.class);

    private RentalDao rentalDao;

    public RentalServiceImpl(RentalDao rentalDao) {
        this.rentalDao = rentalDao;
    }

    @Override
    public List<Rental> findAll() {
        LOGGER.debug("Find all rentals");
        return rentalDao.findAll();
    }

    @Override
    public List<Rental> findByCarId(Integer carId) {
        LOGGER.debug("findByCarId({})", carId);
        return rentalDao.findByCarId(carId);
    }

    @Override
    public Rental findById(Integer rentalId) {
        LOGGER.debug("findById({})", rentalId);
        return rentalDao.findById(rentalId)
                .orElseThrow(() -> new RuntimeException("Failed to get rental from DB"));
    }

    @Override
    public Rental add(Rental rental) {
        LOGGER.debug("add({})", rental);
        return rentalDao.add(rental);
    }

    @Override
    public void update(Rental rental) {
        LOGGER.debug("update({})", rental);
        rentalDao.update(rental);
    }

    @Override
    public void delete(Integer rentalId) {
        LOGGER.debug("delete({})", rentalId);
        rentalDao.delete(rentalId);
    }
}
