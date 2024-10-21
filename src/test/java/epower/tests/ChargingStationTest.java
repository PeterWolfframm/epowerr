package epower.tests;

import static org.junit.jupiter.api.Assertions.*;


import org.epower.model.ChargingStation;
import org.epower.model.Customer;
import org.epower.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ChargingStationTest {

    private ChargingStation station;
    private Customer customer;

    @BeforeEach
    void setUp() {
        station = new ChargingStation("CS1001", "Meidling", "AC");
        customer = new Customer("user123", "John Doe");
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
        station.startChargingSession(customer, transaction);
        assertEquals("in operation occupied", station.getStatus());

        // Simulate ending the session
        transaction.setEnergyConsumed(15);  // 15 kWh consumed
        station.endChargingSession();
        assertEquals("in operation free", station.getStatus());
        assertNull(station.getCurrentTransaction());
    }
}