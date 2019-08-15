package com.epam.brest.summer.courses2019.model;

/**
 * POJO Car for model.
 */
public class Car {

    /**
     * Car Brand.
     */
    private String carBrand;

    /**
     * Car Id.
     */
    private Integer carId;


    /**
     * Car Year.
     */
    private Integer carYear;

    /**
     * Car Engine.
     */
    private String carEngine;

    /**
     * Car Gearbox.
     */
    private String carGearbox;

    /**
     * Car Class.
     */
    private String carClass;

    /**
     * Constructor without arguments.
     */
    public Car() {
    }

    /**
     * Constructor with department name.
     *
     * @param carBrand department name
     */
    public Car(String carBrand) {
        this.carBrand = carBrand;
    }

    /**
     * Returns <code>String</code> representation of this carBrand.
     *
     * @return carBrand Car Brand.
     */
    public String getCarBrand() {
        return carBrand;
    }

    /**
     * Sets the car's brand.
     *
     * @param carBrand Car Brand.
     */
    public void setCarBrand(final String carBrand) { this.carBrand = carBrand; }

    /**
     * Returns <code>Integer</code> representation of this carYear.
     *
     * @return carYear Car Year.
     */
    public Integer getCarYear() { return carYear; }

    /**
     * Sets the car's year.
     *
     * @param carYear Car Year.
     */
    public void setCarYear(final Integer carYear) {
        this.carYear = carYear;
    }

    /**
     * Returns <code>String</code> representation of this carEngine.
     *
     * @return carEngine Car Engine.
     */
    public String getCarEngine() { return carEngine; }

    /**
     * Sets the car's engine.
     *
     * @param carEngine Car Engine.
     */
    public void setCarEngine(final String carEngine) {
        this.carEngine = carEngine;
    }

    /**
     * Returns <code>String</code> representation of this carGearbox.
     *
     * @return carGearbox Car Gearbox.
     */
    public String getCarGearbox() { return carGearbox; }

    /**
     * Sets the car's gearbox.
     *
     * @param carGearbox Car Gearbox.
     */
    public void setCarGearbox(final String carGearbox) { this.carGearbox = carGearbox; }

    /**
     * Returns <code>String</code> representation of this carClass.
     *
     * @return carClass Car Class.
     */
    public String getCarClass() { return carClass; }

    /**
     * Sets the car's class.
     *
     * @param carClass Car Class.
     */
    public void setCarClass(final String carClass) { this.carClass = carClass; }

    /**
     * Returns <code>Integer</code> representation of this carId.
     *
     * @return carId Car Id.
     */
    public Integer getCarId() { return carId; }

    /**
     * Sets the car's id.
     *
     * @param carId Car Id.
     */
    public void setCarId(final Integer carId) { this.carId = carId; }

    @Override
    public String toString() {
        return "Car{"
                + "carBrand=" + carBrand
                + ",carId=" + carId + '\''
                + ", carYear='" + carYear + '\''
                + ", carEngine=" + carEngine + '\''
                + ", carGearbox=" + carGearbox + '\''
                + ", carClass=" + carClass
                + '}';
    }
}
