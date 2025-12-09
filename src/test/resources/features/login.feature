Feature: Login

  Rule: A registered user can only log in

    Scenario Outline: Successful login
      Given the user is on the login page
      When the user enters <username> and <password> password
      And clicks the login button
      Then the registered user should be redirected to the dashboard
      Examples:
        | username    | password      |
        | tester01    | Kjhgfdsa.9    |
        | invaliduser | wrongpassword |
