Feature: Profile management

  Rule: A logged-in user can update their profile information

    Example: Update user's first name
      Given the user is logged in
      And the user is on the profile page
      When the user updates their first name to "John"
      And saves the changes
      Then the welcome message should contain "John"