Feature: Deposit transaction

  Rule: A user can deposit funds into their accounts

    Scenario Outline: Deposit to an account
      Given the user is logged in
      And the user is on the deposit into account page
      When the user makes an individual cheking deposit of <amount>
      Then the new transaction for <amount> should be visible in the transaction list
      And the account balance should be updated accordingly

      Examples:
        | amount  |
        | 500     |
        | 10.50   |


