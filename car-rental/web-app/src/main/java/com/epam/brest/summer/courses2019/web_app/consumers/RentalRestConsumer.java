package com.epam.brest.summer.courses2019.web_app.consumers;

import com.epam.brest.summer.courses2019.model.Rental;
import com.epam.brest.summer.courses2019.service.RentalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class RentalRestConsumer implements RentalService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RentalRestConsumer.class);

    private String url;

    private RestTemplate restTemplate;

    public RentalRestConsumer(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Rental> findAll() {
        return null;
    }

    @Override
    public List<Rental> findByCarId(Integer carId) {
        LOGGER.debug("findByCarId({})", carId);
        ResponseEntity<Rental> responseEntity = restTemplate.getForEntity(url + "/" + carId, Rental.class);
        return (List<Rental>) responseEntity.getBody();
    }

    @Override
    public Rental findById(Integer rentalId) {
        LOGGER.debug("findById({})", rentalId);
        ResponseEntity<Rental> responseEntity = restTemplate.getForEntity(url + "/" + rentalId, Rental.class);
        return responseEntity.getBody();
    }

    @Override
    public Rental add(Rental rental) {
        LOGGER.debug("add({})", rental);
        ResponseEntity responseEntity = restTemplate.postForEntity(url, rental, Rental.class);
        Object result = responseEntity.getBody();
        return (Rental) result;
    }

    @Override
    public void update(Rental rental) {
        LOGGER.debug("update({})", rental);
        restTemplate.put(url, rental);
    }

    @Override
    public void delete(Integer rentalId) {
        LOGGER.debug("delete({})", rentalId);
        restTemplate.delete(url + "/" + rentalId);
    }
}
























