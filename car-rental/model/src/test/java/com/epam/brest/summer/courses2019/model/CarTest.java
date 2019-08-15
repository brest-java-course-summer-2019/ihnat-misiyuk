package com.epam.brest.summer.courses2019.model;

import org.junit.Assert;
import org.junit.Test;

public class CarTest {

    Car car = new Car();

    @Test
    public void getCarBrand(){
        car.setCarBrand("Audi");
        Assert.assertTrue(car.getCarBrand().equals("Audi"));
    }

    @Test
    public void getCarId(){
        car.setCarId(3);
        Assert.assertTrue(car.getCarId().equals(3));
    }

    @Test
    public void getCarYear(){
        car.setCarYear(1988);
        Assert.assertTrue(car.getCarYear().equals(1988));
    }

    @Test
    public void getCarEngine(){
        car.setCarEngine("2.3");
        Assert.assertTrue(car.getCarEngine().equals("2.3"));
    }

    @Test
    public void getCarGearbox(){
        car.setCarGearbox("Mechanical");
        Assert.assertTrue(car.getCarGearbox().equals("Mechanical"));
    }

    @Test
    public void getCarClass(){
        car.setCarClass("Business");
        Assert.assertTrue(car.getCarClass().equals("Business"));
    }
}
