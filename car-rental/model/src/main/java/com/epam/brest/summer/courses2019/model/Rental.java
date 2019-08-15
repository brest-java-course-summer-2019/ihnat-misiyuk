package com.epam.brest.summer.courses2019.model;

/**
 * POJO Rental for model.
 */
public class Rental {

    /**
     * Rental Id.
     */
    private Integer rentalId;

    /**
     * Rental Days.
     */
    private Integer rentalDays;

    /**
     * Rental Rate.
     */
    private String rentalRate;

    /**
     * Rental Price.
     */
    private Integer rentalPrice;

    public Rental() {
    }

    public Rental(Integer rentalId, Integer rentalDays, String rentalRate, Integer rentalPrice) {
        this.rentalId = rentalId;
        this.rentalDays = rentalDays;
        this.rentalRate = rentalRate;
        this.rentalPrice = rentalPrice;
    }

    /**
     * Returns <code>Integer</code> representation of this rentalId.
     *
     * @return rentalId Rental Id.
     */
    public Integer getRentalId() { return rentalId; }

    /**
     * Sets the rental's id.
     *
     * @param rentalId Rental Id.
     */
    public void setRentalId(Integer rentalId) { this.rentalId = rentalId; }

    /**
     * Returns <code>Integer</code> representation of this rentalDays.
     *
     * @return rentalDays Rental Days.
     */
    public Integer getRentalDays() { return rentalDays; }

    /**
     * Sets the rental's days.
     *
     * @param rentalDays Rental Days.
     */
    public void setRentalDays(Integer rentalDays) { this.rentalDays = rentalDays; }

    /**
     * Returns <code>String</code> representation of this rentalRate.
     *
     * @return rentalRate Rental Rate.
     */
    public String getRentalRate() { return rentalRate; }

    /**
     * Sets the rental's rate.
     *
     * @param rentalRate Rental Rate.
     */
    public void setRentalRate(String rentalRate) { this.rentalRate = rentalRate; }

    /**
     * Returns <code>Integer</code> representation of this rentalPrice.
     *
     * @return rentalPrice Rental Price.
     */
    public Integer getRentalPrice() { return rentalPrice; }

    /**
     * Sets the rental's price.
     *
     * @param rentalPrice Rental Price.
     */
    public void setRentalPrice(Integer rentalPrice) { this.rentalPrice = rentalPrice; }

    @Override
    public String toString() {
        return "Rental{"
                + "rentalId=" + rentalId
                + ",rentalDays=" + rentalDays + '\''
                + ", rentalRate='" + rentalRate + '\''
                + ", rentalPrice=" + rentalPrice
                + '}';
    }
}
