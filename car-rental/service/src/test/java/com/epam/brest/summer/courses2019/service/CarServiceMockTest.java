package com.epam.brest.summer.courses2019.service;

import com.epam.brest.summer.courses2019.dao.CarDao;
import com.epam.brest.summer.courses2019.model.Car;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(MockitoExtension.class)
public class CarServiceMockTest {

    @Mock
    private CarDao dao;

    @Captor
    private ArgumentCaptor<Car> carCaptor;

    @InjectMocks
    private CarServiceImpl service;

    @AfterEach
    void after() {
        Mockito.verifyNoMoreInteractions(dao);
    }

    @Test
    void findAll() {

        Mockito.when(dao.findAll()).thenReturn(Collections.singletonList(create()));

        List<Car> result = service.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());

        Mockito.verify(dao).findAll();
    }

    @Test
    void findById() {

        int id = 1;

        Mockito.when(dao.findById(id)).thenReturn(Optional.of(create()));

        Car car = service.findById(id);

        assertNotNull(car);
        assertEquals("brand", car.getCarBrand());

        Mockito.verify(dao).findById(id);
    }

    @Test
    void update() {

        service.update(create());

        Mockito.verify(dao).update(carCaptor.capture());

        Car car = carCaptor.getValue();
        assertNotNull(car);
        assertEquals("brand", car.getCarBrand());
    }

    @Test
    void delete() {

        int id = 3;

        service.delete(id);

        Mockito.verify(dao).delete(id);
    }

    private Car create() {
        Car car = new Car();
        car.setCarBrand("brand");
        return car;
    }
}
