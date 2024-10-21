package org.epower.model;


import java.time.LocalDateTime;

public class Transaction {

    private String transactionId;
    private String stationId;
    private String mode;
    private double duration;
    private double energyConsumed;
    private double pricePerKWh;
    private double totalPrice;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Transaction(String transactionId, String stationId, String mode, double pricePerKWh) {
        this.transactionId = transactionId;
        this.stationId = stationId;
        this.mode = mode;
        this.pricePerKWh = pricePerKWh;
        this.startTime = LocalDateTime.now();
    }

    // Getters and Setters
    public String getTransactionId() {
        return transactionId;
    }

    public String getStationId() {
        return stationId;
    }

    public String getMode() {
        return mode;
    }

    public double getDuration() {
        return duration;
    }

    public double getEnergyConsumed() {
        return energyConsumed;
    }

    public double getPricePerKWh() {
        return pricePerKWh;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
        this.duration = java.time.Duration.between(startTime, endTime).toMinutes();
    }

    public double calculateTotalPrice() {
        this.totalPrice = this.energyConsumed * this.pricePerKWh;
        return this.totalPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setEnergyConsumed(double energyConsumed) {
        this.energyConsumed = energyConsumed;
    }
}