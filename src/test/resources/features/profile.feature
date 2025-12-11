@profile
Feature: Profile management

  Rule: A logged-in user can update their profile information

    Scenario Outline: Update user's name
      Given the user is logged in
      And the user is on the profile page
      When the user updates their title to "<title>", first name to "<first_name>" and last name to "<last_name>"
      Then a success message "Profile Updated Successfully." is displayed

      Examples:
        | title | first_name | last_name |
        | Mr.   | Johnn      | Doe       |
        | Ms.   | Jane       | Doe       |