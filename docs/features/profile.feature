Feature: Profile management

  Rule: A logged-in user can update their profile information

    Scenario Outline: Update user's first name
      Given the user is logged in
      And the user is on the profile page
      When the user updates their first name to "<new_name>"
      And saves the changes
      Then the welcome message should contain "<new_name>"

      Examples:
        | new_name |
        | John     |
        | Jane     |