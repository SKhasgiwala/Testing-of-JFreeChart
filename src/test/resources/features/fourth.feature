@fourth

Feature: Cloning of Legend title
  As a user of the chart library
  I want to clone the legend title of a chart
  In order to create duplicates of it

  Scenario: To check for successful cloning of legend title
    Given I have an initial XYchart with dataset and a legend title
    When I clone the legend title
    Then The two legend title must not be equal
    And The two legend titles must represent same class
    And They are independent of each other