package com.smartbank.account;

import java.math.BigDecimal;
import java.util.List;

import com.smartbank.account.exceptions.InsufficientFundsException;
import com.smartbank.account.model.Account;
import com.smartbank.account.model.AccountType;
import com.smartbank.person.Person;
import com.smartbank.person.PersonService;

public class AccountService
{
   private final AccountRepository accountRepository;

   private final PersonService personService;

   public AccountService( final AccountRepository accountRepository, final PersonService personService )
   {
      this.accountRepository = accountRepository;
      this.personService = personService;
   }


   public Account createNewAccount( final Long personId, final AccountType accountType )
   {
      final Person person = this.personService.findById( personId );
      if ( person == null )
      {
         return null;
      }
      final Account account = new Account( personId, accountType );
      this.accountRepository.save( account );
      return account;
   }


   public void deposit( final Long accountId, final BigDecimal amount )
   {
      final Account account = this.accountRepository.findById( accountId );
      account.deposit( amount );
   }


   public void withdraw( final Long accountId, final BigDecimal amount ) throws InsufficientFundsException
   {
      final Account account = this.accountRepository.findById( accountId );
      try
      {
         account.withdraw( amount );
      }
      catch ( IllegalStateException e )
      {
         throw new InsufficientFundsException( "Not enough funds", e );
      }
   }


   public Long balance( final Long accountId )
   {
      final Account account = this.accountRepository.findById( accountId );
      return account.getBalance().longValue();
   }


   public List<Account> findAll()
   {
      return this.accountRepository.findAll();
   }


   public Account findById( final Long accountId )
   {
      return this.accountRepository.findById( accountId );
   }
}
