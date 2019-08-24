package com.epam.brest.summer.courses2019.service;

import com.epam.brest.summer.courses2019.model.Car;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:../resources/test-service.xml"})
public class CarServiceImplTest {

    @Autowired
    private CarService carService;

    @Test
    void findAll() {
        List<Car> cars = carService.findAll();

        assertNotNull(cars);
        assertFalse(cars.isEmpty());
    }

    @Test
    void findById() {
        int id = 1;
        Car car = carService.findById(id);

        assertNotNull(car);
        assertEquals("AUDI", car.getCarBrand());
    }

    @Test
    void update() {
        int id = 2;
        Car car = create();
        car.setCarId(id);
        carService.update(car);
        car = carService.findById(id);

        assertNotNull(car);
        assertEquals("brand", car.getCarBrand());
    }

    @Test
    void delete() {
        int id = 3;
        carService.delete(id);
        assertThrows(RuntimeException.class, () -> carService.findById(id));
    }

    @Test
    void add() {
        long count = carService.findAll().size();
        assertThrows(DuplicateKeyException.class, () -> carService.add(create(), create()));
        long newCount = carService.findAll().size();
        assertEquals(count, newCount);
    }

    private Car create() {
        Car car = new Car();
        car.setCarBrand("brand");
        return car;
    }
}
