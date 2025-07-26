package com.smartbank.account;

import java.math.BigDecimal;
import java.util.List;

import com.smartbank.account.exceptions.InsufficientFundsException;
import com.smartbank.account.model.Account;
import com.smartbank.account.model.AccountType;
import com.smartbank.person.Person;
import com.smartbank.person.PersonService;
import com.smartbank.utility.IDService;

public class AccountService
{
   private final AccountRepository accountRepository;

   private final PersonService personService;

   public AccountService( AccountRepository accountRepository, PersonService personService )
   {
      this.accountRepository = accountRepository;
      this.personService = personService;
   }


   public Account createNewAccount( String ownerId, String type )
   {
      Person person = this.personService.findById( Long.parseLong( ownerId ) );
      if ( person == null )
      {
         return null;
      }
      Account account = new Account( IDService.fromString( ownerId ), AccountType.valueOf( type ) );
      accountRepository.save( account );
      return account;
   }


   public void deposit( String accountId, BigDecimal amount )
   {
      Account account = accountRepository.findById( accountId );
      account.deposit( amount );
   }


   public void withdraw( String accountId, BigDecimal amount ) throws InsufficientFundsException
   {
      Account account = accountRepository.findById( accountId );
      try
      {
         account.withdraw( amount );
      }
      catch ( IllegalStateException e )
      {
         throw new InsufficientFundsException( "Not enough funds", e );
      }
   }


   public void applyInterestToSavings( double rate )
   {
      for ( Account acc : accountRepository.findAll() )
      {
         if ( acc.getType() == AccountType.SAVINGS )
         {
            acc.applyInterest( rate );
         }
      }
   }


   public List<Account> findRichAccounts( BigDecimal threshold )
   {
      return accountRepository.findAll().stream().filter( acc -> acc.getBalance().compareTo( threshold ) > 0 )
            .sorted( ( a, b ) -> b.getBalance().compareTo( a.getBalance() ) ).toList();
   }


   public List<Account> findAll()
   {
      return this.accountRepository.findAll();
   }


   public Account findById( String id )
   {
      return this.accountRepository.findById( id );
   }
}
