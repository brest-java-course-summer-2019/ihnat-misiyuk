package com.epam.brest.summer.courses2019.service;

import com.epam.brest.summer.courses2019.model.Car;
import com.epam.brest.summer.courses2019.dao.CarDao;
import com.epam.brest.summer.courses2019.dao.CarStubDao;
import com.epam.brest.summer.courses2019.model.stub.CarStub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 *  Car Service Interface implementation.
 */
@Component
@Transactional
public class CarServiceImpl implements CarService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarServiceImpl.class);

    private CarDao carDao;

    private CarStubDao carStubDao;

    public CarServiceImpl(CarDao carDao, CarStubDao carStubDao) {
        this.carDao = carDao;
        this.carStubDao = carStubDao;
    }

    @Override
    public List<Car> findAll() {
        LOGGER.debug("Find all cars");
        return carDao.findAll();
    }

    @Override
    public List<CarStub> findAllWithRequestedGearbox() {
        LOGGER.debug("Find all cars with requested gearbox");
        return carStubDao.findAllWithRequestedGearbox();
    }

    @Override
    public Car findById(Integer id) {
        LOGGER.debug("findById({})", id);
        return carDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Failed to get car from DB"));
    }

    @Override
    public void update(Car car) {
        LOGGER.debug("update({})", car);
        carDao.update(car);
    }

    @Override
    public void delete(int id) {
        LOGGER.debug("delete({})", id);
        carDao.delete(id);
    }

    @Override
    public void add(Car... cars) {
        for (Car car : cars) {
            carDao.add(car);
        }
    }

    @Override
    public Car add(Car car) {
        return carDao.add(car);
    }
}
