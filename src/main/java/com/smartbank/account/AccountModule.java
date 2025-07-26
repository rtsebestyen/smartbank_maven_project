package com.smartbank.account;

import com.smartbank.commoninterface.ModuleInterface;
import com.smartbank.person.PersonService;

import io.javalin.Javalin;

public class AccountModule implements ModuleInterface
{

   private final AccountService accountService;

   private final AccountRestController accountRestController;

   public AccountModule( final PersonService personService )
   {
      final AccountRepository accountRepository = new AccountRepository();
      this.accountService = new AccountService( accountRepository, personService );
      this.accountRestController = new AccountRestController( this.accountService );
   }


   public AccountService getService()
   {
      return this.accountService;
   }


   @Override
   public void initializeModule( final Javalin javalin )
   {
      this.accountRestController.startController( javalin );
   }
}
