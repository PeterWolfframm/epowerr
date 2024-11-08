package org.epower.model;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private String customerId;
    private String name;
    private double balance;
    private List<Transaction> transactions;

    public Customer(String customerId, String name) {
        this.customerId = customerId;
        this.name = name;
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
    }

    // Getters and Setters
    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactionHistory() {
        return transactions;
    }

    public void topUp(double amount) {
        if(amount > 0){
            this.balance += amount;
        }
    }

    public boolean canStartCharging(double pricePerKWh) {
        return this.balance >= pricePerKWh;
    }

    public void startTransaction(Transaction transaction) {
        if(this.balance>0){
            transactions.add(transaction);
        }
        else System.out.println("Pay your existing bills first please.");
    }

    public void endTransaction(Transaction transaction) {
        this.balance -= transaction.calculateTotalPrice();
        transaction.setEndTime(java.time.LocalDateTime.now());
    }
    // helpFunc
    public void getBalanceBelow0(int x){
        this.balance -= x;
    }
}