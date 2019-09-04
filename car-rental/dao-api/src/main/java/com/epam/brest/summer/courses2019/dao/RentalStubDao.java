package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.stub.RentalStub;

import java.util.List;

/**
 * RentalStub DAO Interface.
 */
public interface RentalStubDao {

    /**
     * Get all rental with requested price.
     *
     * @return rentals list.
     */
    List<RentalStub> findAllWithRequestedPrice();

}