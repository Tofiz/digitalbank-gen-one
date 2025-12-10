@login
Feature: Login

  Rule: A registered user can only log in

    @successful
    Scenario Outline: Successful login
      Given the user is on the login page
      When the user enters "<username>" and "<password>"
      And clicks the login button
      Then the registered user should be redirected to the dashboard
      Examples:
        | username | password   |
        | jsmith   | Demo123!   |
        | tester01 | Kjhgfdsa.9 |

Rule: Aa unregistered user can not log in

    @unsuccessful
    Scenario Outline: Unsuccessful login
      Given the user is on the login page
      When the user enters "<invalid_username>" and "<invalid_password>"
      And clicks the login button
      Then the registered user should not be redirected to the dashboard
      Examples:
        | invalid_username | invalid_password |
        | tester01         |                  |
        |                  | Kjhgfdsa.9       |
        |                  |                  |