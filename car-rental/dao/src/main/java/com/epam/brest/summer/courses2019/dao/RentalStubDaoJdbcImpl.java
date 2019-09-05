package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.stub.RentalStub;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Rental DAO Interface implementation.
 */
@Component
public class RentalStubDaoJdbcImpl implements RentalStubDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${rentalStub.findAllWithRequestedPrice}")
    private String findAllWithRequestedPrice;

    public RentalStubDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<RentalStub> findAllWithRequestedPrice() {
        List<RentalStub> rentals = namedParameterJdbcTemplate.query(findAllWithRequestedPrice,
                BeanPropertyRowMapper.newInstance(RentalStub.class));
        return rentals;
    }
}