@withdraw
Feature: withdraw transaction

  Rule: A user can withdraw amount from their accounts

    Scenario Outline: Withdraw to an account
      Given the user is logged in
      And the user is on the withdraw from account page
      When the user makes an individual cheking withdraw of "<amount>"
      Then the new transaction for "<amount>" should be visible in the transaction list
      And the account balance should be updated accordingly

      Examples:
        | amount  |
        | 500     |
        | 10.50   |