package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.stub.CarStub;

import java.util.List;

/**
 * CarStub DAO Interface.
 */
public interface CarStubDao {

    /**
     * Get all cars with requested gearbox.
     *
     * @return cars list.
     */
    List<CarStub> findAllWithRequestedGearbox();

}