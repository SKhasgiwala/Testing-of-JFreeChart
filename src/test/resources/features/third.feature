@third
Feature: Replace dataset for Area chart
  As a user of the chart library
  I want to replace the data values of the area chart
  In order to create charts for different input values

  Scenario: To check for replacing of the dataset in Area chart
    Given I have an initial chart with a dataset
    When I replace the dataset with new data values
    Then I check that the new dataset is OK
