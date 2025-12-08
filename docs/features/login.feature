Feature: Login and Logout

  Rule: A registered user can log in and out

    Scenario: Successful login and logout
      Given the user is on the login page
      When the user enters "tester01" username and "correct_password" password
      And clicks the login button
      Then the user should be redirected to the dashboard
      When the user clicks on the user avatar
      And clicks the logout button
      Then the user should be redirected to the login page

  Rule: The system prevents access with invalid credentials

    Scenario Outline: Unsuccessful login with invalid credentials
      Given the user is on the login page
      When the user enters "imvalid_user" username and "<wrong_password>" password
      And clicks the login button
      Then an error message should be displayed with text "Invalid credentials or access not granted due to user account status or an existing user session."

      Examples:
        | username      | password      |
        | invaliduser   | wrongpassword |
        | tester01      | Kjhgfdsa.9 |