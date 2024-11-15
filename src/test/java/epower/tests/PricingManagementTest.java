package epower.tests;

import org.epower.model.PricingManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PricingManagementTest {

    private PricingManagement pricingManagement;

    @BeforeEach
    void setUp() {
        pricingManagement = new PricingManagement();
    }

    @Test
    void testSetAndGetACPrice() {
        pricingManagement.setACPrice("Meidling", 0.30);
        assertEquals(0.30, pricingManagement.getACPrice("Meidling"));
    }

    @Test
    void testSetAndGetDCPrice() {
        pricingManagement.setDCPrice("Meidling", 0.50);
        assertEquals(0.50, pricingManagement.getDCPrice("Meidling"));
    }

    @Test
    void testGetPriceForACCharging() {
        pricingManagement.setACPrice("Meidling", 0.30);
        assertEquals(0.30, pricingManagement.getPrice("Meidling", "AC"));
    }

    @Test
    void testGetPriceForDCCharging() {
        pricingManagement.setDCPrice("Meidling", 0.50);
        assertEquals(0.50, pricingManagement.getPrice("Meidling", "DC"));
    }

    // ERROR CASE TESTS
    @Test
    void testSetNegativeACPrice(){
        pricingManagement.setACPrice("Meidling", -1.5);
        assertEquals(0.0, pricingManagement.getPrice("Meidling", "AC"));
    }
    @Test
    void testSetNegativeDCPrice(){
        pricingManagement.setDCPrice("Meidling", -1.5);
        assertEquals(0.0, pricingManagement.getPrice("Meidling", "DC"));
    }
}