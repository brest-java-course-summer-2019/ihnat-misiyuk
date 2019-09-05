package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.Rental;
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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class RentalDaoJdbcImpl implements RentalDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

//    @Value("${rental.findAll}")
//    private String findAllSql;
//
//    @Value("${rental.findById}")
//    private String findByIdSql;
//
//    @Value("${rental.findByCarId}")
//    private String findByCarIdSql;
//
//    @Value("${rental.insert}")
//    private String insertSql;
//
//    @Value("${rental.update}")
//    private String updateSql;
//
//    @Value("${rental.delete}")
//    private String deleteSql;

    private final static String SELECT_ALL =
            "select rental_id, rental_rate, car_id from rental order by 1";

    private static final String FIND_BY_ID =
            "select rental_id, rental_rate, car_id from rental where rental_id = :rentalId";

    private static final String FIND_BY_CAR_ID =
            "select rental_id, rental_rate, car_id from rental where car_id = :carId";

    private final static String ADD_RENTAL =
            "insert into rental (rental_id, rental_rate, car_id) values (:rentalId, :rentalRate, :car_id)";

    private static final String UPDATE =
            "update rental set rental_days = :rentalDays, rental_rate = :rentalRate, rental_price = :rentalPrice, " +
                    "car_id = :carId where rental_id = :rentalId";

    private static final String DELETE =
            "delete from rental where rental_id = :rentalId";

    private static final String CAR_ID = "carId";
    private static final String RENTAL_ID = "rentalId";

    public RentalDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Rental> findAll() {
        List<Rental>rentals = namedParameterJdbcTemplate.query(SELECT_ALL, new RentalRowMapper());
        return rentals;
    }

    @Override
    public List<Rental> findByCarId(Integer carId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource(CAR_ID, carId);
        List<Rental> results = namedParameterJdbcTemplate.query(FIND_BY_CAR_ID, namedParameters,
                BeanPropertyRowMapper.newInstance(Rental.class));
        return results;
    }

    @Override
    public Optional<Rental> findById(Integer rentalId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource(RENTAL_ID, rentalId);
        List<Rental> results = namedParameterJdbcTemplate.query(FIND_BY_ID, namedParameters,
                BeanPropertyRowMapper.newInstance(Rental.class));
        return Optional.ofNullable(DataAccessUtils.uniqueResult(results));
    }

    @Override
    public Rental add(Rental rental) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("rentalRate", rental.getRentalRate());

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(ADD_RENTAL, parameters, generatedKeyHolder);
        rental.setRentalId(generatedKeyHolder.getKey().intValue());
        return rental;
    }

    @Override
    public void update(Rental rental) {
        if (namedParameterJdbcTemplate.update(UPDATE, new BeanPropertySqlParameterSource(rental)) < 1) {
            throw new EmptyResultDataAccessException(
                    String.format("Failed to update. '%s' not found in the DB", rental), 1);
        }
    }

    @Override
    public void delete(Integer rentalId) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(RENTAL_ID, rentalId);
        Optional.of(namedParameterJdbcTemplate.update(DELETE, mapSqlParameterSource))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to delete rental from DB"));
    }

    private boolean successfullyUpdated(int numRowsUpdated) {
        return numRowsUpdated > 0;
    }

    private class RentalRowMapper implements RowMapper<Rental> {
        @Override
        public Rental mapRow(ResultSet resultSet, int i) throws SQLException {
            Rental rental = new Rental();
            rental.setRentalId(resultSet.getInt("rental_id"));
            rental.setRentalRate(resultSet.getString("rental_rate"));
            return rental;
        }
    }
}
















//public class RentalDaoJdbcImpl implements RentalDao {
//
//    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
//
//    private final static String SELECT_ALL =
//            "select rental_id, rental_days, rental_rate, rental_price from rental order by 1, 3";
//
//    private static final String FIND_BY_ID =
//            "select rental_id, rental_days, rental_rate, rental_price " +
//                    "from rental where rental_id = :rentalId";
//
//    private final static String ADD_RENTAL =
//            "insert into rental (rental_id, rental_days, rental_rate, rental_price) " +
//                    "values (:rentalId, :rentalDays, :rentalRate, :rentalPrice)";
//
//    private static final String UPDATE =
//            "update rental set rental_days = :rentalDays, rental_rate = :rentalRate, rental_price = :rentalPrice, " +
//                    "where rental_id = :rentalId";
//
//    private static final String DELETE =
//            "delete from rental where rental_id = :rentalId";
//
//    private static final String CAR_ID = "carId";
//    private static final String RENTAL_ID = "rentalId";
//
//    public RentalDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
//        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
//    }
//
//    @Override
//    public List<Rental> findAll(){
//        List<Rental> rentals =
//                namedParameterJdbcTemplate.query(SELECT_ALL, BeanPropertyRowMapper.newInstance(Rental.class));
//        return rentals;
//    }
//
//    @Override
//    public Optional<Rental> findById(Integer rentalId){
//        SqlParameterSource namedParameters = new MapSqlParameterSource(RENTAL_ID, rentalId);
//        List<Rental> results = namedParameterJdbcTemplate.query(FIND_BY_ID, namedParameters,
//                BeanPropertyRowMapper.newInstance(Rental.class));
//        return Optional.ofNullable(DataAccessUtils.uniqueResult(results));
//    }
//
//    @Override
//    public Rental add(Rental rental){
//        MapSqlParameterSource parameters = new MapSqlParameterSource();
//        parameters.addValue("rentalDays", rental.getRentalDays());
//        parameters.addValue("rentalRate", rental.getRentalRate());
//        parameters.addValue("rentalPrice", rental.getRentalPrice());
//
//        KeyHolder generateKeyHolder = new GeneratedKeyHolder();
//        namedParameterJdbcTemplate.update(ADD_RENTAL, parameters, generateKeyHolder);
//        rental.setRentalId(generateKeyHolder.getKey().intValue());
//        return rental;
//    }
//
//    @Override
//    public void update(Rental rental){
//        Optional.of(namedParameterJdbcTemplate.update(UPDATE, new BeanPropertySqlParameterSource(rental)))
//                .filter(this::successfullyUpdated)
//                .orElseThrow(() -> new RuntimeException("Failed to update rental in DB"));
//    }
//
//    @Override
//    public void delete(Integer rentalId){
//        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
//        mapSqlParameterSource.addValue(RENTAL_ID, rentalId);
//        Optional.of(namedParameterJdbcTemplate.update(DELETE, mapSqlParameterSource))
//                .filter(this::successfullyUpdated)
//                .orElseThrow(() -> new RuntimeException("Failed to delete rental from DB"));
//    }
//
//    private boolean successfullyUpdated(int numRowsUpdated) {
//        return numRowsUpdated > 0;
//    }
//}















