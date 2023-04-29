Feature: Navigation to CCS home page

  @Test1 @Regression @sfc69
  Scenario Outline: User launches the browser and navigate to CCS home page
    Given user logs in to the CCS application for "ScenarioID"
    When User enter ccs url
    Then User should be navigated to CCS home page
    Examples:
      | ScenarioID |
      | Scenario 1 |

  @Test
  Scenario Outline: To verify user is displayed GM landing page
    Given user logs in to the CCS application for "<ScenarioID>"
    And User is navigated to CCS home page
    When User enters "<framework>" details
    Then User sees valid search result count

    Examples:
      | ScenarioID | framework |
      | Scenario 1 | Linen     |







