package epower.tests;


import org.epower.model.ChargingStation;

import org.epower.model.Customer;
import org.epower.model.Location;
import org.epower.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    private Customer customer;
    private Location l;
    private ChargingStation cs;
    private Transaction n;

    @BeforeEach
    void setUp() {
        customer = new Customer("user123", "John Doe");
        customer.setBalance(100.0);  // Set initial balance
        l = new Location("Testort");
        cs = new ChargingStation("1234", "Testort", "AC");
        n = new Transaction("1234", "1234", "AC", 3.12);
    }

    @Test
    void testGetName() {
        assertEquals("John Doe", customer.getName());
    }

    @Test
    void testGetBalance() {
        assertEquals(100.0, customer.getBalance());
    }

    @Test
    void testTopUpBalance() {
        customer.topUp(50.0);
        assertEquals(150.0, customer.getBalance());
    }

    @Test
    void testCanStartChargingSufficientBalance() {
        assertTrue(customer.canStartCharging(30.0));
    }

    @Test
    void testCanStartChargingInsufficientBalance() {
        assertFalse(customer.canStartCharging(120.0));
    }

    @Test
    void testStartAndEndTransaction() {
        Transaction transaction = new Transaction("T1001", "CS1001", "AC", 0.30);
        customer.startTransaction(transaction);
        transaction.setEnergyConsumed(20);  // Simulate 20 kWh consumed
        customer.endTransaction(transaction);
        assertEquals(94.0, customer.getBalance());  // (100 - 20 kWh * 0.30 EUR/kWh)
    }

    //ERROR CASE TEST
    @Test
    void testTopUpBalanceNegativeAmount() {
        customer.topUp(-50.0);
        assertEquals(100.0, customer.getBalance());
    }
  
    // EDGE CASE TEST
    @Test
    void testNegativeAmountAfterTransaction(){
        customer.setBalance(-5);
        List<Transaction> before = customer.getTransactionHistory();
        customer.startTransaction(n);
        assertEquals(customer.getTransactionHistory(), before);
    }
    @Test
    void positveAmountAfterTopUp(){
        customer.setBalance(-5);
        customer.topUp(100);
        customer.startTransaction(n);
        assertNotEquals(customer.getTransactionHistory().size(), 0);
    }

}