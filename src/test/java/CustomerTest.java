
import static org.junit.jupiter.api.Assertions.*;

import org.epower.model.Customer;
import org.epower.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomerTest {

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer("user123", "John Doe");
        customer.setBalance(100.0);  // Set initial balance
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
}