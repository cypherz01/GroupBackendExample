package com.example.groupdemo.service;

import com.example.groupdemo.model.Driver;

public class FactorCalculator {
    public double calculateVehicleTypeFactor(String vehicleType){

        double vehicleTypeFactor = 0.00;

        switch(vehicleType){
            case "Cabriolet":
                vehicleTypeFactor = 1.3;
                break;
            case "Coupe":
                vehicleTypeFactor = 1.4;
                break;
            case "Estate":
                vehicleTypeFactor = 1.5;
                break;
            case "Hatchback":
                vehicleTypeFactor = 1.6;
                break;
            case "Other":
                vehicleTypeFactor = 1.7;
                break;
            default:
                vehicleTypeFactor = 100;
                break;
        }
        return vehicleTypeFactor;
    }

    public double calculateEngineSizeFactor(int engineSize){

        double engineSizeFactor = 0;

        switch(engineSize){
            case 1000:
                engineSizeFactor = 1.0;
                break;
            case 1600:
                engineSizeFactor = 1.6;
                break;
            case 2000:
                engineSizeFactor = 2.0;
                break;
            case 2500:
                engineSizeFactor = 2.5;
                break;
            case 3000:
                engineSizeFactor = 3.0;
                break;
            default:
                engineSizeFactor = 3.5;
                break;
        }
        return engineSizeFactor;
    }

    public double calculateVehicleValueFactor(double value){
        double vehicleValueFactor = 0.0;

        vehicleValueFactor = (value <= 5000 ? 1.0 : 1.2);

        return vehicleValueFactor;
    }

    public double calculateAdditionalDriversFactor(double additionalDrivers){
        double additionalDriversFactor = 0.0;

        additionalDriversFactor = (additionalDrivers < 2 ? 1.1 : 1.2);

        return additionalDriversFactor;
    }

    public double calculateCommercialUseFactor(boolean commercialUse){
        double commercialUseFactor = 0.0;

        commercialUseFactor = (commercialUse ? 1.1 : 1.0);

        return commercialUseFactor;
    }

    public double calculateOutsideStateUseFactor(boolean outsideStateUse){
        double outsideStateUseFactor = 0.0;

        outsideStateUseFactor = (outsideStateUse ? 1.1 : 1.0);

        return outsideStateUseFactor;
    }

    public double calculateInsurcanceQuote(Driver driver){
        return 100 * (
                calculateVehicleTypeFactor(driver.getVehicleType()) *
                        calculateEngineSizeFactor(driver.getEngineSize()) *
                        calculateAdditionalDriversFactor(driver.getAdditionalDrivers()) *
                        calculateCommercialUseFactor(driver.isCommercial()) *
                        calculateOutsideStateUseFactor(driver.isRegisteredOutsideState()) *
                        calculateVehicleValueFactor(driver.getVehicleValue())
        );
    }
}
