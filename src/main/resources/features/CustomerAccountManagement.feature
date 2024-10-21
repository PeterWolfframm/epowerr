Feature: Manage Customer Accounts

  As an operator
  I want to manage customer accounts
  So that customers can use the charging stations and maintain their accounts.

  Scenario: Register a new customer
    Given a user wants to register for a charging account
    When they provide all the required registration information
    Then a new customer account should be created
    And they should receive a unique customer identity

  Scenario: Customer tops up their account
    Given a customer with ID "C67890" has a balance of "20.00" EUR
    When the customer tops up their account with "30.00" EUR
    Then the customer's account balance should be "50.00" EUR

  Scenario: Customer starts a charging session with sufficient balance
    Given a customer with ID "C12345" has a balance of "15.00" EUR
    And the price per kWh is "0.25" EUR
    And a charging station "Station_H1" is "in operation free"
    When the customer starts a charging session at "Station_H1" using "AC" mode
    Then the status of "Station_H1" changes to "in operation occupied"
    And the charging session begins

  Scenario: Customer cannot start a charging session with insufficient balance
    Given a customer with ID "C54321" has a balance of "0.00" EUR
    And the price per kWh is "0.25" EUR
    And a charging station "Station_M2" is "in operation free"
    When the customer attempts to start a charging session at "Station_M2" using "DC" mode
    Then the charging session should not start
    And the customer should be notified of insufficient funds

  Scenario: Customer views account balance and history
    Given a customer with ID "C67890"
    When the customer views their account details
    Then they should see their current balance
    And a list of all past charging sessions and top-ups, sorted by date
    And details like session number, location, charging point, mode, duration, energy consumed, and price