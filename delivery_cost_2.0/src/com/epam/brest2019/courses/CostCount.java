package com.epam.brest2019.courses;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Properties;
import java.util.Scanner;

public class CostCount {
    private Scanner scanner;
    private Properties properties;

    public CostCount() throws IOException {
        this.scanner = new Scanner(System.in);
        this.properties = new Properties();

        FileInputStream fileInputStream = new FileInputStream("resources/cost.properties");
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        properties.load(bufferedInputStream);
    }

    public String getCost(){
        System.out.println("Enter the weight in kilograms or 'q' to exit");
        BigDecimal weight = getValue();

        System.out.println("Enter the distance in kilometers or 'q' to exit");
        BigDecimal distance = getValue();
        return sum(weight, distance);
    }

    private BigDecimal getValue(){
        BigDecimal value = null;
        String input;
        while(value == null){
            input = scanner.nextLine();
            if(!input.toLowerCase().equals('q')){
                try {
                    value = new BigDecimal(input);
                } catch (NumberFormatException e){
                    System.out.println("Wrong value. Try again or enter 'q' to quit");
                    value = null;
                }
            } else{
                System.out.println("Closing application");
                break;
            }
        }

        return value;
    }

    private String sum(BigDecimal weight, BigDecimal distance){
        BigDecimal pricePerKm;
        BigDecimal pricePerKg = new BigDecimal(10);
        BigDecimal result;
        if(distance.compareTo(new BigDecimal(100)) == 1){
            pricePerKm = new BigDecimal(Integer.parseInt(properties.getProperty("lessThan100")));
        } else{
            pricePerKm = new BigDecimal(Integer.parseInt(properties.getProperty("moreThan100")));
        }
        return weight.multiply(pricePerKg).add(distance.multiply(pricePerKm)).toString();
    }
}
