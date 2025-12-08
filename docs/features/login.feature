Feature: Login and Logout

  Scenario: Successful login and logout
    Given the user is on the login page
    When the user enters valid credentials
    And clicks the login button
    Then the user should be redirected to the dashboard
    When the user clicks on the user avatar
    And clicks the logout button
    Then the user should be redirected to the login page

  Scenario: Unsuccessful login
    Given the user is on the login page
    When the user enters invalid credentials
    And clicks the login button
    Then an error message should be displayed
