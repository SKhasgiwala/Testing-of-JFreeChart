@first
  Feature: Pie chart creation
    As a user of the library
    I want to create and display a pie chart
    In order to represent my information graphically

  Scenario: Create a Pie chart
    Given I have the dataset
    When I populate the values in chart
    And set it to visible
    Then pie chart is displayed to me