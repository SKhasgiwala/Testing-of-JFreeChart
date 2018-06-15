@second
Feature: Replace dataset functionality
  As a developer of the chart library
  I want to check the behaviour of program in case of replacing dataset values with null
  In order to check the receiving of notification

  Scenario: To check for replacing of the dataset
    Given I have an initial dataset
    When I replace the dataset with null
    Then I receive a chart change notification
    And The dataset should be null