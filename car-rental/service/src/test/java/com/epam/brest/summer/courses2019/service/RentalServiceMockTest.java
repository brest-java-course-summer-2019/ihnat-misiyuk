package com.epam.brest.summer.courses2019.service;

import com.epam.brest.summer.courses2019.dao.RentalDao;
import com.epam.brest.summer.courses2019.model.Rental;
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
public class RentalServiceMockTest {

    @Mock
    private RentalDao dao;

    @Captor
    private ArgumentCaptor<Rental> rentalCaptor;

    @InjectMocks
    private RentalServiceImpl service;

    @AfterEach
    void after() {
        Mockito.verifyNoMoreInteractions(dao);
    }

    @Test
    void findAll() {

        Mockito.when(dao.findAll()).thenReturn(Collections.singletonList(create()));

        List<Rental> result = service.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());

        Mockito.verify(dao).findAll();
    }

    @Test
    void findById() {

        int id = 1;

        Mockito.when(dao.findById(id)).thenReturn(Optional.of(create()));

        Rental rental = service.findById(id);

        assertNotNull(rental);
        assertEquals("normal", rental.getRentalRate());

        Mockito.verify(dao).findById(id);
    }

    @Test
    void update() {

        service.update(create());

        Mockito.verify(dao).update(rentalCaptor.capture());

        Rental rental = rentalCaptor.getValue();
        assertNotNull(rental);
        assertEquals("brand", rental.getRentalRate());
    }

    @Test
    void delete() {

        int id = 3;

        service.delete(id);

        Mockito.verify(dao).delete(id);
    }

    private Rental create() {
        Rental rental = new Rental();
        rental.setRentalRate("normal");
        return rental;
    }
}









