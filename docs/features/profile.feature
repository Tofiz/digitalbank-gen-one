Feature: Profile management

  Scenario: Update user profile
    Given the user is logged in
    And the user is on the profile page
    When the user updates their first name
    And saves the changes
    Then the welcome message should reflect the new first name