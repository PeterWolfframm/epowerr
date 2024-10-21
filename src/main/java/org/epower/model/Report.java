package org.epower.model;


import java.util.List;

public class Report {

    public Report() {
    }

    public List<Transaction> generateCustomerReport(Customer customer) {
        return customer.getTransactionHistory();
    }

    public double calculateTotalEnergyConsumed(List<Transaction> transactions) {
        return transactions.stream().mapToDouble(Transaction::getEnergyConsumed).sum();
    }

    public double calculateTotalAmountCharged(List<Transaction> transactions) {
        return transactions.stream().mapToDouble(Transaction::getTotalPrice).sum();
    }
}