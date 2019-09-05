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

//    @Value("${carStub.findAllWithRequestedGearbox}")
//    private String findAllWithRequestedGearbox;

    private final static String SELECT_ALL_WITH_REQ_GEARBOX =
            "select car_id, car_brand, car_year, car_engine, car_gearbox, car_class from car where car_gearbox = :carGearbox";

    public CarStubDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<CarStub> findAllWithRequestedGearbox() {
        List<CarStub> cars = namedParameterJdbcTemplate.query(SELECT_ALL_WITH_REQ_GEARBOX,
                BeanPropertyRowMapper.newInstance(CarStub.class));
        return cars;
    }
}
