Feature: Navigation to CCS home page_Table transformer

  @Test3 @Regression @sfc69 @Table1
  Scenario Outline: User launches the browser and navigate to CCS home page
    Given user logs in to the CCS application
    |ScenarioID |
    |<ScenarioID>|
    When User enter ccs url
    Then User should be navigated to CCS home page
    Examples:
      | ScenarioID |
      | Scenario 1 |