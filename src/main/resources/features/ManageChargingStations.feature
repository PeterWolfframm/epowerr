Feature: Manage Charging Stations

  As an operator
  I want to manage charging stations
  So that I can ensure efficient operation and maintenance.

  Scenario: View status of charging stations at a location
    Given there are multiple charging stations at "Berlin"
    When I view the status of charging stations at "Berlin"
    Then I should see each charging station's type ('AC' or 'DC') and current status ('in operation free', 'in operation occupied', 'out of service')

  Scenario: Change status of a charging station to "out of service"
    Given a charging station "Station_A1" at "Hamburg" is "in operation free"
    When I change the status of "Station_A1" to "out of service"
    Then "Station_A1" should have the status "out of service"

  Scenario: Add a new charging station
    Given I need to add a new charging station at "Munich"
    When I add a "DC" charging station named "Station_M3" at "Munich"
    Then "Station_M3" should be listed among the charging stations at "Munich" with status "in operation free"

  Scenario: Remove a charging station
    Given there is a charging station "Station_F2" at "Frankfurt"
    When I remove "Station_F2" from the system
    Then "Station_F2" should no longer appear in the list of charging stations at "Frankfurt"

  Scenario: Update charging station type
    Given a charging station "Station_B4" at "Berlin" is of type "AC"
    When I update the type of "Station_B4" to "DC"
    Then "Station_B4" should now be listed as type "DC"

  # Error Cases:
  Scenario: Change name of charging Station to "AA" (anything other than "AC" or "DC")
    Given I am an operator
    And there is a location with a station
    When I try to update the type of "Station_B4" to "AA"
    Then the type of "Station_B4" should not change

  # Edge Cases:
  Scenario: Set charging station status to out of order
    Given I am an operator
    When a charging station is out of order
    And I change the status to "Out of order"
    Then the status should be updated
