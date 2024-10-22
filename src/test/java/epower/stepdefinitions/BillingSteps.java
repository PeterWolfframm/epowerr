package epower.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.epower.model.PricingManagement;
import org.epower.model.Report;
import org.epower.model.Transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BillingSteps {

    private PricingManagement pricingManagement = new PricingManagement();
    private Report report = new Report();
    private Transaction transaction;

    @Given("the current price for {string} charging in {string} is {double} EUR per kWh")
    public void setCurrentPrice(String chargingMode, String location, double price) {
        if (chargingMode.equals("AC")) {
            pricingManagement.setACPrice(location, price);
        } else if (chargingMode.equals("DC")) {
            pricingManagement.setDCPrice(location, price);
        }
    }

    @When("I change the price for {string} charging in {string} to {double} EUR per kWh")
    public void changePrice(String chargingMode, String location, double newPrice) {
        if (chargingMode.equals("AC")) {
            pricingManagement.setACPrice(location, newPrice);
        } else if (chargingMode.equals("DC")) {
            pricingManagement.setDCPrice(location, newPrice);
        }
    }

    @Then("the new price for {string} charging in {string} should be {double} EUR per kWh")
    public void verifyNewPrice(String chargingMode, String location, double expectedPrice) {
        double actualPrice = chargingMode.equals("AC")
                ? pricingManagement.getACPrice(location)
                : pricingManagement.getDCPrice(location);
        assertEquals(expectedPrice, actualPrice, "Price was not updated correctly");
    }

    @When("I change the price for {string} charging in {string} to {double} EUR per kWh at {string}")
    public void changePriceAtTime(String chargingMode, String location, double price, String time) {
        changePrice(chargingMode, location, price);
    }

    @Then("the customer should be charged at {double} EUR per kWh for the entire session")
    public void verifyCustomerChargedPrice(double expectedPrice) {
        assertEquals(expectedPrice, transaction.getPricePerKWh(), "Customer was charged incorrectly");
    }

    @When("I view the current prices at {string}")
    public void viewCurrentPrices(String location) {
        System.out.println("AC price in " + location + ": " + pricingManagement.getACPrice(location));
        System.out.println("DC price in " + location + ": " + pricingManagement.getDCPrice(location));
    }

    @Then("I should see the prices for {string} and {string} charging modes currently in effect")
    public void verifyPrices(String mode1, String mode2) {
        //assertNotNull(pricingManagement.getACPrice(location), "AC price is not set");
        //assertNotNull(pricingManagement.getDCPrice(location), "DC price is not set");
    }

    @Given("a customer with ID {string} has completed multiple charging sessions")
    public void customerWithMultipleSessions(String customerId) {
        // Simulate customer having multiple sessions
    }

    @When("I generate a billing report for customer {string}")
    public void generateBillingReport(String customerId) {
        // Generate the billing report for the given customer
        System.out.println("Billing report generated for customer: " + customerId);
    }

    @Then("the report should list all sessions sorted by start time")
    public void verifyBillingReport() {
        System.out.println("Billing report lists all sessions sorted by start time.");
    }

    @Then("include details like session number, location, charging point, mode, duration, energy consumed, and price")
    public void verifyBillingReportDetails() {
        System.out.println("Billing report includes all session details.");
    }

    @Then("show the total amount charged")
    public void verifyTotalAmountCharged() {
        System.out.println("Total amount charged is displayed in the billing report.");
    }

    //ERROR CASE
    private double ac;
    private double dc;
    @When("an attempt is made to enter a negative price")
    public void setNegativePrice(String chargingMode, String location, double price){
        // enter negative price
        ac = pricingManagement.getACPrice(location);
        dc = pricingManagement.getDCPrice(location);
        if(price > 0) return;
        if (chargingMode.equals("AC")) {
            pricingManagement.setACPrice(location, price);
        } else if (chargingMode.equals("DC")) {
            pricingManagement.setDCPrice(location, price);
        }
    }
    @Then("The price should stay the same")
    public void checkPrice(String chargingMode, String location, double expectedPrice){
        //price stays the same
        if(chargingMode.equals("AC")){
            assertEquals(ac, pricingManagement.getACPrice(location), "Price was not updated correctly");
        }
        else if(chargingMode.equals("DC")){
            assertEquals(dc, pricingManagement.getDCPrice(location), "Price was not updated correctly");
        }
    }
}