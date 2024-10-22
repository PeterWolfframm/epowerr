package org.epower.model;


public class ChargingStation {

    private String stationId;
    private String location;
    private String type;
    private String status;
    private Customer currentCustomer;
    private Transaction currentTransaction;

    public ChargingStation(String stationId, String location, String type) {
        this.stationId = stationId;
        this.location = location;
        this.type = type;
        this.status = "in operation free";
    }

    // Getters and Setters
    public String getStationId() {
        return stationId;
    }

    public String getLocation() {
        return location;
    }

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }

    public void startChargingSession(Customer customer, Transaction transaction) {
        this.currentCustomer = customer;
        this.currentTransaction = transaction;
        updateStatus("in operation occupied");
    }

    public void endChargingSession() {
        this.currentTransaction.setEndTime(java.time.LocalDateTime.now());
        updateStatus("in operation free");
        this.currentCustomer.endTransaction(this.currentTransaction);
        this.currentCustomer = null;
        this.currentTransaction = null;
    }

    public void updateType(String newType) {
        if(newType.equals("AC") || newType.equals("DC")){
            this.type= newType;
        }
    }
}