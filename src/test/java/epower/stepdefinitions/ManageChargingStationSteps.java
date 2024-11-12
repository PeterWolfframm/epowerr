package epower.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.epower.model.ChargingStation;
import org.epower.model.Location;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ManageChargingStationSteps {

    private Location location;
    private ChargingStation currentStation;
    private List<ChargingStation> stationList;

    @Given("there are multiple charging stations at {string}")
    public void setUpChargingStations(String locationName) {
        location = new Location(locationName);
        ChargingStation station1 = new ChargingStation("CS1001", locationName, "AC");
        ChargingStation station2 = new ChargingStation("CS1002", locationName, "DC");
        location.addChargingStation(station1);
        location.addChargingStation(station2);
        stationList = location.getStations();
    }

    @When("I view the status of charging stations at {string}")
    public void viewChargingStationStatus(String locationName) {
        System.out.println("Charging stations at " + locationName + ":");
        for (ChargingStation station : stationList) {
            System.out.println("Station ID: " + station.getStationId() + ", Status: " + station.getStatus());
        }
    }

    @Then("I should see each charging station's type {string} and current status {string}")
    public void verifyChargingStationStatus(String type, String status) {
        for (ChargingStation station : stationList) {
            assertEquals(type, station.getType(), "Type mismatch for station: " + station.getStationId());
            assertEquals(status, station.getStatus(), "Status mismatch for station: " + station.getStationId());
        }
    }

    @When("I change the status of {string} to {string}")
    public void changeStationStatus(String stationId, String newStatus) {
        currentStation = location.getStationById(stationId);
        currentStation.updateStatus(newStatus);
    }

    @Then("{string} should have the status {string}")
    public void verifyStationStatus(String stationId, String expectedStatus) {
        ChargingStation station = location.getStationById(stationId);
        assertEquals(expectedStatus, station.getStatus(), "Station status not updated correctly");
    }
    //ERROR CASE
    @When("I try to change the status of {string} to an invalid status")
    public void changeStationStatusInvalid(String stationId) {
        currentStation = location.getStationById(stationId);
        currentStation.updateStatus("AA");
    }

    @Then("{string} should not change the status")
    public void verifyStationStatusInvalidInput(String stationId) throws Exception {
        ChargingStation station = location.getStationById(stationId);
        if(station.getStatus().equals("AC")|| station.getStatus().equals("DC")){
            // passt
        }
        else{
            throw new Exception("Ungültiger Status");
        }
    }

    @When("I add a {string} charging station named {string} at {string}")
    public void addChargingStation(String type, String stationName, String locationName) {
        ChargingStation newStation = new ChargingStation(stationName, locationName, type);
        location.addChargingStation(newStation);
        System.out.println("Added new station: " + stationName);
    }

    @Then("{string} should be listed among the charging stations at {string} with status {string}")
    public void verifyNewStation(String stationName, String locationName, String status) {
        ChargingStation station = location.getStationById(stationName);
        assertNotNull(station, "Station not found: " + stationName);
        assertEquals(status, station.getStatus(), "Status mismatch for new station");
    }

    @When("I remove {string} from the system")
    public void removeChargingStation(String stationId) {
        location.removeChargingStation(stationId);
        System.out.println("Station " + stationId + " removed.");
    }

    @Then("{string} should no longer appear in the list of charging stations at {string}")
    public void verifyStationRemoval(String stationId, String locationName) {
        assertNull(location.getStationById(stationId), "Station " + stationId + " was not removed.");
    }

    @When("I update the type of {string} to {string}")
    public void updateChargingStationType(String stationId, String newType) {
        ChargingStation station = location.getStationById(stationId);
        station.updateType(newType);
    }

    @Then("{string} should now be listed as type {string}")
    public void verifyUpdatedStationType(String stationId, String expectedType) {
        ChargingStation station = location.getStationById(stationId);
        assertEquals(expectedType, station.getType(), "Station type not updated correctly");
    }
    //ERROR CASE
    @When("I try to change the status of {string} to an invalid status")
    public void changeStationStatusInvalid(String stationId) {
        currentStation = location.getStationById(stationId);
        currentStation.updateStatus("AA");
    }

    @Then("{string} should not change the status")
    public void verifyStationStatusInvalidInput(String stationId) throws Exception {
        ChargingStation station = location.getStationById(stationId);
        if(station.getStatus().equals("AC")|| station.getStatus().equals("DC")){
            // passt
        }
        else{
            throw new Exception("Ungültiger Status");
        }
    }
    // EDGE Cases
    ChargingStation x = location.getStationById("CS1001");
    String type= x.getStatus();
    @When("A charging station is out of order and I update the status")
    public void updateStatusOutOfOrder(){
        x.updateType("Out of Order");
    }

    @Then("The status should be updated")
    public void checkStatus(){
        assertEquals(type, x.getStatus(), "Status mismatch");
    }
}