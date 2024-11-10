package epower.tests;

import org.epower.model.ChargingStation;
import org.epower.model.Customer;
import org.epower.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChargingStationTest {

    private ChargingStation station;
    private Customer customer;
    List<Transaction> l;
    Transaction transaction;

    @BeforeEach
    void setUp() {
        station = new ChargingStation("CS1001", "Meidling", "AC");
        customer = new Customer("user123", "John Doe");
        l =  customer.getTransactionHistory();
        transaction = new Transaction("123", "CS1001", "AC", 2.56);
    }

    @Test
    void testGetStationId() {
        assertEquals("CS1001", station.getStationId());
    }

    @Test
    void testGetLocation() {
        assertEquals("Meidling", station.getLocation());
    }

    @Test
    void testUpdateStatus() {
        station.updateStatus("in operation occupied");
        assertEquals("in operation occupied", station.getStatus());
    }

    @Test
    void testStartAndEndChargingSession() {
        Transaction transaction = new Transaction("T1001", station.getStationId(), "AC", 0.30);
        customer.topUp(100);
        station.startChargingSession(customer, transaction);
        assertEquals("in operation occupied", station.getStatus());

        // Simulate ending the session
        transaction.setEnergyConsumed(15);  // 15 kWh consumed
        station.endChargingSession();
        assertEquals("in operation free", station.getStatus());
        assertNull(station.getCurrentTransaction());
    }

    // ERROR CASE TEST
    @Test
    void testWrongType(){
        station.updateType("AA");
        assertNotEquals("AA", station.getType());
    }

    // EDGE CASE TEST
    @Test
    void testNotEnoughBalanceForCharge(){
        List<Transaction> before = customer.getTransactionHistory();
        customer.startTransaction(transaction);
        assertEquals(customer.getTransactionHistory(),before);
    }

    @Test
    void testSetStatusOutOfOrder(){
        String b = station.getStatus();
        station.updateType("Out of Order");
        assertEquals(b, station.getStatus());
    }

}