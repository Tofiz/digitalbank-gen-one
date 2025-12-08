Feature: Money Transactions

  Scenario: Deposit to checking account
    Given the user is logged in
    And the user is on the checking account page
    When the user makes a deposit
    Then the new transaction should be visible in the transaction list
    And the account balance should be updated

  Scenario: Deposit to savings account
    Given the user is logged in
    And the user is on the savings account page
    When the user makes a deposit
    Then the new transaction should be visible in the transaction list
    And the account balance should be updated
