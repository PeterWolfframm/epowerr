package epower.tests;

import static org.junit.jupiter.api.Assertions.*;


import org.epower.model.ChargingStation;
import org.epower.model.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LocationTest {

    private Location location;
    private ChargingStation station1, station2;

    @BeforeEach
    void setUp() {
        location = new Location("Meidling");
        station1 = new ChargingStation("CS1001", "Meidling", "AC");
        station2 = new ChargingStation("CS1002", "Meidling", "DC");
    }

    @Test
    void testAddChargingStation() {
        location.addChargingStation(station1);
        assertEquals(1, location.getStations().size());
    }

    @Test
    void testRemoveChargingStation() {
        location.addChargingStation(station1);
        location.addChargingStation(station2);
        location.removeChargingStation("CS1001");
        assertEquals(1, location.getStations().size());
    }

    @Test
    void testGetStationById() {
        location.addChargingStation(station1);
        ChargingStation foundStation = location.getStationById("CS1001");
        assertNotNull(foundStation);
        assertEquals("CS1001", foundStation.getStationId());
    }
}