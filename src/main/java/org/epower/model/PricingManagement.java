package org.epower.model;

import java.util.HashMap;
import java.util.Map;

public class PricingManagement {

    private Map<String, Double> acPricesByLocation;
    private Map<String, Double> dcPricesByLocation;

    public PricingManagement() {
        this.acPricesByLocation = new HashMap<>();
        this.dcPricesByLocation = new HashMap<>();
    }

    // Getters and Setters
    public void setACPrice(String location, double newPrice) {
        if(newPrice > 0){
            acPricesByLocation.put(location, newPrice);
        }
    }

    public void setDCPrice(String location, double newPrice) {
        if(newPrice > 0){
            dcPricesByLocation.put(location, newPrice);
        }
    }

    public double getACPrice(String location) {
        return acPricesByLocation.getOrDefault(location, 0.0);
    }

    public double getDCPrice(String location) {
        return dcPricesByLocation.getOrDefault(location, 0.0);
    }

    public double getPrice(String location, String mode) {
        if (mode.equals("AC")) {
            return getACPrice(location);
        } else if (mode.equals("DC")) {
            return getDCPrice(location);
        }
        return 0.0;
    }
}