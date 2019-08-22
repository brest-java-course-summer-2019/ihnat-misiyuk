package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.Rental;

import java.util.List;
import java.util.Optional;

/**
 * Rental DAO Interface.
 */
public interface RentalDao {
    /**
     * Get all rentals.
     *
     * @return list of all rentals
     */
    List<Rental> findAll();

    /**
     * Get all rentals with specified car id.
     *
     * @param carId car id
     * @return list of rentals by car id
     */
    List<Rental> findByCarId(Integer carId);

    /**
     * Get rental with specified id.
     *
     * @param rentalId rental id
     * @return rental by id
     */
    Optional<Rental> findById(Integer rentalId);

    /**
     * Persist new rental.
     *
     * @param rental rental
     * @return rental
     */
    Rental add(Rental rental);

    /**
     * Update rental.
     *
     * @param rental rental
     */
    void update(Rental rental);

    /**
     * Delete rental with specified id.
     *
     * @param rentalId rental id
     */
    void delete(Integer rentalId);
}