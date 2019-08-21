package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.stub.CarStub;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Car DAO Interface implementation.
 */
@Component
public class CarStubDaoJdbcImpl implements CarStubDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${carStub.findAllWithRequestedGearbox}")
    private String findAllWithRequestedGearbox;

    public CarStubDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<CarStub> findAllWithRequestedGearbox() {
        List<CarStub> cars = namedParameterJdbcTemplate.query(findAllWithRequestedGearbox,
                BeanPropertyRowMapper.newInstance(CarStub.class));
        return cars;
    }
}
