package com.epam.brest.summer.courses2019.model;

import org.junit.Assert;
import org.junit.Test;

public class RentalTest {

    Rental rental = new Rental();

    @Test
    public void getRentalDays() {
        rental.setRentalDays(7);
        Assert.assertTrue(rental.getRentalDays().equals(7));
    }

    @Test
    public void getRentalRate() {
        rental.setRentalRate("Daily");
        Assert.assertTrue(rental.getRentalRate().equals("Daily"));
    }

    @Test
    public void getRentalPrice() {
        rental.setRentalPrice(33);
        Assert.assertTrue(rental.getRentalPrice().equals(33));
    }

}
