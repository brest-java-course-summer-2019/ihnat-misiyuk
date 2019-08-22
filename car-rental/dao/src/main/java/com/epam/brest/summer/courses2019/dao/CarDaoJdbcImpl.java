package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.Car;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 *  Car DAO Interface implementation.
 */
public class CarDaoJdbcImpl implements CarDao{

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${car.findAll}")
    private String findAllSql;

    @Value("${car.findById}")
    private String findByIdSql;

    @Value("${car.insert}")
    private String insertSql;

    @Value("${car.update}")
    private String updateSql;

    @Value("${car.delete}")
    private String deleteSql;

    private static final String CAR_ID = "carId";

    public CarDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Car add(Car car) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("carBrand", car.getCarBrand());

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(insertSql, parameters, generatedKeyHolder);
        car.setCarId(generatedKeyHolder.getKey().intValue());
        return car;
    }

    @Override
    public void update(Car car) {
        if (namedParameterJdbcTemplate.update(updateSql, new BeanPropertySqlParameterSource(car)) < 1) {
            throw new EmptyResultDataAccessException(
                    String.format("Failed to update. '%s' not found in the DB", car), 1);
        }
    }

    private boolean successfullyUpdated(int numRowsUpdated) {
        return numRowsUpdated > 0;
    }

    @Override
    public void delete(Integer carId) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(CAR_ID, carId);
        Optional.of(namedParameterJdbcTemplate.update(deleteSql, mapSqlParameterSource))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to delete car from DB"));
    }

    @Override
    public List<Car> findAll() {
        List<Car> cars = namedParameterJdbcTemplate.query(findAllSql, new CarRowMapper());
        return cars;
    }

    @Override
    public Optional<Car> findById(Integer carId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource(CAR_ID, carId);
        List<Car> results = namedParameterJdbcTemplate.query(findByIdSql, namedParameters,
                BeanPropertyRowMapper.newInstance(Car.class));
        return Optional.ofNullable(DataAccessUtils.uniqueResult(results));
    }

    private class CarRowMapper implements RowMapper<Car> {
        @Override
        public Car mapRow(ResultSet resultSet, int i) throws SQLException {
            Car car = new Car();
            car.setCarId(resultSet.getInt("car_id"));
            car.setCarBrand(resultSet.getString("car_brand"));
            return car;
        }
    }
}



//@Component
//public class CarDaoJdbcImpl implements CarDao {
//
//    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
//
//    private final static String SELECT_ALL =
//            "select d.car_brand, d.car_id, d.car_year, d.car_engine, d.car_gearbox, d.car_class from car d order by 2";
//
//    private static final String FIND_BY_ID =
//            "select car_id, car_brand, car_year, car_engine, car_gearbox, car_class from car where car_id = :carId";
//
////    private final static String ADD_CAR =
////            "insert into car (car_id, car_brand, car_year, car_engine, car_gearbox, car_class) " +
////                    "values (:carId, :carBrand, :carYear, :carEngine, :carGearbox, :carClass)";
//
//    private final static String ADD_CAR =
//            "insert into car (car_id, car_brand) values (:carId, :carBrand)";
//
////    спросить про необходимость добавления остальных полей (не только в этом методе)
//    private static final String UPDATE =
//        "update car set car_brand = :carBrand, car_year = :carYear, car_engine = :carEngine, " +
//                "car_gearbox = :carGearbox, car_class = :carClass where car_id = :carId";
//
//    private static final String DELETE =
//            "delete from car where car_id = :carId";
//
//    private static final String CAR_ID = "carId";
//
//    public CarDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
//        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
//    }
//
//    @Override
//    public Car add(Car car) {
//        MapSqlParameterSource parameters = new MapSqlParameterSource();
//        parameters.addValue("carBrand", car.getCarBrand());
////        добавлять ли так же остальные поля?
////        parameters.addValue("carYear", car.getCarYear());
////        parameters.addValue("carEngine", car.getCarEngine());
////        parameters.addValue("carGearbox", car.getCarGearbox());
////        parameters.addValue("carClass", car.getCarClass());
//
//        KeyHolder generateKeyHolder = new GeneratedKeyHolder();
//        namedParameterJdbcTemplate.update(ADD_CAR, parameters, generateKeyHolder);
//        car.setCarId(generateKeyHolder.getKey().intValue());
//        return car;
//    }
//
//    @Override
//    public void update(Car car) {
//        Optional.of(namedParameterJdbcTemplate.update(UPDATE, new BeanPropertySqlParameterSource(car)))
//                .filter(this::successfullyUpdated)
//                .orElseThrow(() -> new RuntimeException("Failed to update car in DB"));
//    }
//
//    private boolean successfullyUpdated(int numRowsUpdated) {
//        return numRowsUpdated > 0;
//    }
//
//    @Override
//    public void delete(Integer carId) {
//        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
//        mapSqlParameterSource.addValue(CAR_ID, carId);
//        Optional.of(namedParameterJdbcTemplate.update(DELETE, mapSqlParameterSource))
//                .filter(this::successfullyUpdated)
//                .orElseThrow(() -> new RuntimeException("Failed to delete car from DB"));
//    }
//
//    @Override
//    public List<Car> findAll() {
//        List<Car> cars = namedParameterJdbcTemplate.query(SELECT_ALL, new CarRowMapper());
//        return cars;
//    }
//
//    @Override
//    public Optional<Car> findById(Integer carId){
//        SqlParameterSource namedParameters = new MapSqlParameterSource(CAR_ID, carId);
//        List<Car> results = namedParameterJdbcTemplate.query(FIND_BY_ID, namedParameters,
//                BeanPropertyRowMapper.newInstance(Car.class));
//        return Optional.ofNullable(DataAccessUtils.uniqueResult(results));
//    }
//
//    private class CarRowMapper implements RowMapper<Car> {
//        @Override
//        public Car mapRow(ResultSet resultSet, int i) throws SQLException {
//            Car car = new Car();
//            car.setCarId(resultSet.getInt("car_id"));
//            car.setCarBrand(resultSet.getString("car_brand"));
////            car.setCarYear(resultSet.getInt("car_year"));
////            car.setCarEngine(resultSet.getString("car_engine"));
////            car.setCarGearbox(resultSet.getString("car_gearbox"));
////            car.setCarClass(resultSet.getString("car_class"));
//            return car;
//        }
//    }
//
//}














