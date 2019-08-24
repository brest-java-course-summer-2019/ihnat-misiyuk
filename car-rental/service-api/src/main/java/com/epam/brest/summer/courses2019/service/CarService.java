package com.epam.brest.summer.courses2019.service;

import com.epam.brest.summer.courses2019.model.Car;
import com.epam.brest.summer.courses2019.model.stub.CarStub;

import java.util.List;

/**
 * Car Service Interface.
 */
public interface CarService {

    /**
     * Find all cars stream.
     *
     * @return cars.
     */
    List<Car> findAll();

    /**
     * Get all cars with requested gearbox.
     *
     * @return departments list.
     */
    List<CarStub> findAllWithRequestedGearbox();

    /**
     * Find Car By Id.
     *
     * @param id id
     * @return Car
     */
    Car findById(Integer id);

    /**
     * Update car.
     *
     * @param car car
     */
    void update(Car car);

    /**
     * Delete Car.
     *
     * @param id car id
     */
    void delete(int id);

    void add(Car... cars);

    /**
     * Add Car.
     *
     * @param car car
     * @return Car
     */
    Car add(Car car);
}
