Feature: Money Transactions

  Rule: A user can deposit funds into their accounts

    Scenario Outline: Deposit to an account
      Given the user is logged in
      And the user is on the "<account_type>" account page
      When the user makes a deposit of <amount>
      Then the new transaction for <amount> should be visible in the transaction list
      And the account balance should be updated accordingly

      Examples:
        | account_type | amount |
        | checking     | 500    |
        | savings      | 1000   |