Feature: Login and Logout

  Rule: A registered user can log in and out

    Example: Successful login and logout
      Given the user is on the login page
      When the user enters valid credentials
      And clicks the login button
      Then the user should be redirected to the dashboard
      When the user clicks on the user avatar
      And clicks the logout button
      Then the user should be redirected to the login page

  Rule: The system prevents access with invalid credentials

    Example: Unsuccessful login
      Given the user is on the login page
      When the user enters invalid credentials
      And clicks the login button
      Then an error message should be displayed