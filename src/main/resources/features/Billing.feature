Feature: Administration and Billing

  As an operator
  I want to adjust prices and manage billing
  So that I can respond to changes in demand and accurately bill customers.

  Scenario: Adjust price for charging mode
    Given the current price for "AC" charging in "Berlin" is "0.30" EUR per kWh
    When I change the price for "AC" charging in "Berlin" to "0.35" EUR per kWh
    Then the new price for "AC" charging in "Berlin" should be "0.35" EUR per kWh

  Scenario: Adjust price multiple times a day
    Given the price for "DC" charging in "Munich" is "0.40" EUR per kWh
    When I change the price for "DC" charging in "Munich" to "0.38" EUR per kWh at "10:00 AM"
    And I change the price for "DC" charging in "Munich" to "0.42" EUR per kWh at "2:00 PM"
    Then the current price for "DC" charging in "Munich" should be "0.42" EUR per kWh

  Scenario: Prices apply at start of charging session
    Given the price for "AC" charging in "Hamburg" is "0.28" EUR per kWh
    And a customer starts a charging session at "9:00 AM"
    When the price for "AC" charging in "Hamburg" changes to "0.30" EUR per kWh at "10:00 AM"
    Then the customer should be charged at "0.28" EUR per kWh for the entire session

  Scenario: View current prices at a location
    Given I am an operator
    When I view the current prices at "Frankfurt"
    Then I should see the prices for "AC" and "DC" charging modes currently in effect

  Scenario: Generate billing report for a customer
    Given a customer with ID "C12345" has completed multiple charging sessions
    When I generate a billing report for customer "C12345"
    Then the report should list all sessions sorted by start time
    And include details like session number, location, charging point, mode, duration, energy consumed, and price
    And show the total amount charged