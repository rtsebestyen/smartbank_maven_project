
# Main Features

* Person management
* Account management
* Account transactions:
  * deposit
  * Withdraw
  * move money between accounts
  * send to other account
* Card management
* Loan management
* Currency exchange
* Safety deposit box without account

# Ideas

- Transactionable repos: backup, apply and rollback
- small message broker communication for service including session layer and safety
- cryptography lib
- cripto the messages and requests, rest endpoints
- modularize the bigger service/libs
- loan for person and account
- transaction and query command, TransactionService to work with commands, threadsafe
- Ids should be separate for every entity type, one IDService won't be enough
  Id should be separate: AccountId, PersonId, even parametrizable
- simulate time for tests, create TimeService which can return System or Simulated time

# Endpoints

## Person
GET

http://localhost:7070/persons

http://localhost:7070/persons/{personId}


POST

http://localhost:7070/persons?firstName=name1&lastName=name2&birthday=1996-10-10

## Account
GET

http://localhost:7070/accounts

http://localhost:7070/accounts/{accountId}

http://localhost:7070/accounts/{accountId}/balance


POST

http://localhost:7070/accounts?personId={personId}&accountType=CHECKING

http://localhost:7070/accounts/{accountId}/deposit?amount=100

http://localhost:7070/accounts/{accountId}/withdraw?amount=10


## Card
GET

http://localhost:7070/cards

http://localhost:7070/cards/{cardId}

http://localhost:7070/cards/{cardId}/balance


POST
http://localhost:7070/cards?personId={personId}&accountId={accountId}&cardType=DEBIT

http://localhost:7070/cards/{cardId}/deposit?amount=100

http://localhost:7070/cards/{cardId}/withdraw?amount=10


## Testing sequence

## Person
ID 1 = http://localhost:7070/persons?firstName=Robert&lastName=Seb&birthday=1996-10-10

ID 2 = http://localhost:7070/persons?firstName=Tiberiu&lastName=Seb&birthday=1998-10-10

http://localhost:7070/persons

http://localhost:7070/persons/1

http://localhost:7070/persons/2


## Account
ID 3 = http://localhost:7070/accounts?personId=1&accountType=CHECKING

ID 4 = http://localhost:7070/accounts?personId=2&accountType=SAVINGS

http://localhost:7070/accounts

http://localhost:7070/accounts/3

http://localhost:7070/accounts/4


http://localhost:7070/accounts/3/deposit?amount=100

ACC-1 100 = http://localhost:7070/accounts/3/balance


http://localhost:7070/accounts/4/deposit?amount=100

ACC-2 100 = http://localhost:7070/accounts/4/balance


http://localhost:7070/accounts/3/deposit?amount=100

ACC-1 200 = http://localhost:7070/accounts/3/balance

http://localhost:7070/accounts/3/withdraw?amount=50

ACC-1 150 = http://localhost:7070/accounts/3/balance


ACC-2 100 = http://localhost:7070/accounts/4/balance


## Card
ID 5 = http://localhost:7070/cards?personId=1&accountId=3&cardType=DEBIT

ID 6 = http://localhost:7070/cards?personId=2&accountId=4&cardType=CREDIT

http://localhost:7070/cards

http://localhost:7070/cards/5

http://localhost:7070/cards/6


CARD-1 150 = http://localhost:7070/cards/5/balance

http://localhost:7070/cards/5/deposit?amount=100

CARD-1 250 = http://localhost:7070/cards/5/balance

http://localhost:7070/cards/5/withdraw?amount=10

CARD-1 240 = http://localhost:7070/cards/5/balance


CARD-2 100 = http://localhost:7070/cards/6/balance

http://localhost:7070/cards/6/deposit?amount=100

CARD-2 200 = http://localhost:7070/cards/6/balance


