package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.Car;
import com.epam.brest.summer.courses2019.model.stub.CarStub;
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
public class CarDaoJdbcImplTest {

    private static final String AUDI = "AUDI";
    private static final String HONDA = "HONDA";
    private static final String NEW_HONDA = "NEWHonda";

    @Autowired
    CarDao carDao;

    @Autowired
    CarStubDao carStubDao;

    @Test
    public void findAll() {
        List<Car> cars = carDao.findAll();
        assertNotNull(cars);
        assertTrue(cars.size() > 0);
    }

    @Test
    public void findAllWithRequestedGearbox() {
        List<CarStub> cars = carStubDao.findAllWithRequestedGearbox();
        assertNotNull(cars);
        assertTrue(cars.size() > 0);
    }

    @Test
    public void getCarById() {
        Car car = carDao.findById(1).get();
        assertNotNull(car);
        assertTrue(car.getCarId().equals(1));
        assertEquals(AUDI, car.getCarBrand());
    }

    @Test
    public void addCar() {
        Car testCar = new Car();
        testCar.setCarBrand("NISSAN");
//        Нужно ли добавлять остальные поля?
        Car newCar = carDao.add(testCar);
        assertNotNull(newCar.getCarId());
    }

    @Test
    public void updateCar() {
        Car newCar = new Car(HONDA);
        newCar = carDao.add(newCar);
        newCar.setCarBrand(NEW_HONDA);
        carDao.update(newCar);
        Car updatedCar = carDao.findById(newCar.getCarId()).get();
        assertTrue(newCar.getCarId().equals(updatedCar.getCarId()));
        assertTrue(newCar.getCarBrand().equals(updatedCar.getCarBrand()));
    }

    @Test
    public void deleteCar() {
        Car car = new Car(HONDA);
        car = carDao.add(car);
        List<Car> cars = carDao.findAll();
        int sizeBefore = cars.size();
        carDao.delete(car.getCarId());
        assertTrue((sizeBefore - 1) == carDao.findAll().size());
    }
}













