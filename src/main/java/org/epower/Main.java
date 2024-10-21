package org.epower;


import org.epower.model.*;

public class Main {

    public static void main(String[] args) {
        // Scenario 1: Creating a new ChargingStation and updating its status
        ChargingStation meidlingCharge = new ChargingStation("CS1001", "Meidling", "AC");
        System.out.println("Created new charging station in Meidling:");
        System.out.println("Station ID: " + meidlingCharge.getStationId() + ", Type: " + meidlingCharge.getType());

        // Update status of ChargingStation
        meidlingCharge.updateStatus("in operation free");
        System.out.println("Updated status of Meidling station: " + meidlingCharge.getStatus());

        // Scenario 2: Set prices for the Meidling location using PricingManagement
        PricingManagement pricingManagement = new PricingManagement();
        pricingManagement.setACPrice("Meidling", 0.30);  // Set AC price in Meidling
        pricingManagement.setDCPrice("Meidling", 0.40);  // Set DC price in Meidling
        System.out.println("Price for AC charging in Meidling: " + pricingManagement.getACPrice("Meidling") + " EUR/kWh");
        System.out.println("Price for DC charging in Meidling: " + pricingManagement.getDCPrice("Meidling") + " EUR/kWh");

        // Scenario 3: Creating a customer, topping up balance, and checking the balance
        Customer customer = new Customer("user123", "John Doe");
        customer.setBalance(50.0);
        System.out.println("\nCustomer created:");
        System.out.println("Customer ID: " + customer.getCustomerId() + ", Balance: " + customer.getBalance() + " EUR");

        // Topping up customer balance
        customer.topUp(20.0);
        System.out.println("Customer new balance after top-up: " + customer.getBalance() + " EUR");

        // Scenario 4: Customer starts a charging session
        double pricePerKWh = pricingManagement.getACPrice("Meidling");
        if (customer.canStartCharging(pricePerKWh)) {
            Transaction transaction = new Transaction("T1001", meidlingCharge.getStationId(), "AC", pricePerKWh);
            meidlingCharge.startChargingSession(customer, transaction);
            System.out.println("Charging session started for customer at station: " + meidlingCharge.getStationId());

            // Simulating energy consumption
            transaction.setEnergyConsumed(10);  // 10 kWh consumed

            // End the charging session
            meidlingCharge.endChargingSession();
            System.out.println("Charging session ended. Total price: " + transaction.calculateTotalPrice() + " EUR");
            System.out.println("Customer new balance after charging: " + customer.getBalance() + " EUR");
        } else {
            System.out.println("Customer does not have sufficient balance to start charging.");
        }

        // Scenario 5: Generate a report of all customer transactions
        Report report = new Report();
        System.out.println("\nGenerating report for customer transactions...");
        System.out.println("Total energy consumed: " + report.calculateTotalEnergyConsumed(customer.getTransactionHistory()) + " kWh");
        System.out.println("Total amount charged: " + report.calculateTotalAmountCharged(customer.getTransactionHistory()) + " EUR");
    }
}